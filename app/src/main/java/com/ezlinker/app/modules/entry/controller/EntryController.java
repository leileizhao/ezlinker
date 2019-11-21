package com.ezlinker.app.modules.entry.controller;

import cn.hutool.core.net.NetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.modules.entry.form.UserLoginForm;
import com.ezlinker.app.modules.permission.service.IPermissionService;
import com.ezlinker.app.modules.role.service.IRoleService;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.model.UserDetail;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.app.modules.userlog.model.UserLoginLog;
import com.ezlinker.app.modules.userlog.service.IUserLoginLogService;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.AuthorizedFailedException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import com.ezlinker.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ezlinker
 * @description: 入口
 * @author: wangwenhai
 * @create: 2019-11-11 17:44
 **/
@RestController
@RequestMapping("/entry")
@Slf4j
public class EntryController {

    private static final String LOCAL_IPV4 = "127.0.0.1";
    private static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    IPermissionService iPermissionService;


    @Autowired
    IUserLoginLogService iUserLoginLogService;

    /**
     * 用户登录
     *
     * @param loginForm 登录表单
     * @return
     * @throws AuthorizedFailedException
     */
    @Transactional(rollbackFor = Exception.class, noRollbackFor = AuthorizedFailedException.class)
    @PostMapping("/login")
    public R login(@RequestBody UserLoginForm loginForm, HttpServletRequest request) throws AuthorizedFailedException {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", loginForm.getUsername()));
        String ip = getIp(request);

        if (user == null) {

            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setIp(ip).setStatus("WARNING").setUserId(0L).setRemark("未知用户尝试登陆失败").setLocation(getLocationWithIp(ip));
            iUserLoginLogService.save(userLoginLog);
            throw new AuthorizedFailedException("Login failure,user not exists!", "登陆失败,用户不存在");
        }

        if (!user.getPassword().toUpperCase().equals(SecureUtil.md5(loginForm.getPassword()).toUpperCase())) {
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setIp(ip).setStatus("WARNING").setUserId(user.getId()).setRemark("尝试登陆失败").setLocation(getLocationWithIp(ip));
            iUserLoginLogService.save(userLoginLog);
            throw new AuthorizedFailedException("Login failure,password invalid!", "登陆失败,密码错误");

        }
        /**
         * 构建UserDetail
         */
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetail.setUsername(user.getUsername());
        userDetail.setUserType(user.getUserType());
        userDetail.setExpiredTime(7 * 24 * 60 * 60 * 100L);
        //Roles
        List<String> userRoles = new ArrayList<>();
        userDetail.setRoles(userRoles);
        userDetail.setPermissions(iUserService.getAllPermissions(user.getId()));
        String token = UserTokenUtil.token(userDetail, 24 * 60 * 60 * 1000L);

        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setIp(ip).setStatus("INFO").setUserId(user.getId()).setRemark("登陆成功").setLocation(getLocationWithIp(ip));
        iUserLoginLogService.save(userLoginLog);
        return new R(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMessage(), RCode.SUCCESS.getI8nMessage(), token);

    }

    /**
     * {
     * "code": 0,
     * "data": {
     * "area": "",
     * "country": "中国",
     * "isp_id": "100017",
     * "city": "上海",
     * "ip": "115.159.152.210",
     * "isp": "电信",
     * "county": "XX",
     * "region_id": "310000",
     * "area_id": "",
     * "county_id": "xx",
     * "region": "上海",
     * "country_id": "CN",
     * "city_id": "310100"
     * }
     * }
     *
     * @param ip
     * @return
     */

    private String getLocationWithIp(String ip) {
        if (ip.equals("UN_KNOW")) {
            return "未知IP地址";
        }
        if (NetUtil.isInnerIP(ip)) {
            return "内网登陆,IP:" + ip;
        }

        try {
            String data = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            JSONObject jsonObject = JSONObject.parseObject(data);
            return "IP:" + jsonObject.getString("ip")
                    + ",国家:" + jsonObject.getString("country")
                    + ",地区:" + jsonObject.getString("region")
                    + ",运营商:" + jsonObject.getString("isp");
        } catch (Exception e) {
            log.error("IP获取失败，请检查接口是否正常.");
            return "未知IP地址";
        }
    }

    private String getIp(HttpServletRequest request) {
        String ip;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteHost();
            }
            if (LOCAL_IPV4.equals(ip) || LOCAL_IPV6.equals(ip)) {
                InetAddress inetAddress;
                try {
                    inetAddress = InetAddress.getLocalHost();
                    ip = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    ip = LOCAL_IPV4;
                }
            }
        } catch (Exception e) {
            ip = "UN_KNOW";
        }
        return ip;

    }

}
