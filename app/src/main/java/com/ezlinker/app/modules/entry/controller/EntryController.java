package com.ezlinker.app.modules.entry.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.modules.entry.form.UserLoginForm;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.permission.service.IPermissionService;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.role.service.IRoleService;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.app.modules.userlog.model.UserLoginLog;
import com.ezlinker.app.modules.userlog.service.IUserLoginLogService;
import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import com.ezlinker.common.utils.RedisUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: ezlinker
 * @description: 入口
 * @author: wangwenhai
 * @create: 2019-11-11 17:44
 **/
@RestController
@RequestMapping("/entry")
public class EntryController {

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
     * @throws XException
     */
    @Transactional(rollbackFor = Exception.class, noRollbackFor = XException.class)
    @PostMapping("/login")
    public R login(@RequestBody UserLoginForm loginForm, HttpServletRequest request) throws XException {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", loginForm.getUsername()));
        String ip = getIp(request);

        if (user == null) {

            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setIp(ip).setStatus("WARNING").setUserId(0L).setRemark("未知用户尝试登陆失败").setLocation(getLocationWithIp(ip));
            iUserLoginLogService.save(userLoginLog);
            throw new XException(403, "Login failure,user not exists!", "登陆失败,用户不存在");
        }

        if (!user.getPassword().toUpperCase().equals(SecureUtil.md5(loginForm.getPassword()).toUpperCase())) {
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setIp(ip).setStatus("WARNING").setUserId(user.getId()).setRemark("尝试登陆失败").setLocation(getLocationWithIp(ip));
            iUserLoginLogService.save(userLoginLog);
            throw new XException(403, "Login failure,password invalid!", "登陆失败,密码错误");

        }
        /**
         * 构建UserDetail
         */
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetail.setUsername(user.getUsername());
        userDetail.setUserType(user.getUserType());
        userDetail.setExpiredTime(7 * 24 * 60 * 60 * 100L);
        // 查询Role和Permission
        List<UserRoleView> userRoleViews = iUserService.getRoles(user.getId());
        //Roles
        List<String> userRoles = new ArrayList<>();
        List<String> userPermissions = new ArrayList<>();

        for (UserRoleView userRoleView : userRoleViews) {
            userRoles.add(userRoleView.getName());
            List<RolePermissionView> rolePermissionViews = iUserService.getPermissions(userRoleView.getId());
            for (RolePermissionView rolePermissionView : rolePermissionViews) {
                userPermissions.add(userRoleView.getName() + ":" + rolePermissionView.getName() + ":" + rolePermissionView.getResource());
            }
        }
        userDetail.setRoles(userRoles);
        userDetail.setPermissions(userPermissions);
        String token = UserTokenUtil.token(userDetail, 24 * 60 * 60 * 1000L);
//        try {
//           // redisUtil.set("USER:TOKEN:" + user.getId(), token);
//
//        } catch (Exception e) {
//            throw new XException(500, "Login failure,server internal error!", "登陆失败,服务器内部错误");
//
//        }
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
        if (isInsideNetwork(ip)) {
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
            return "未知";
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
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                InetAddress inetAddress;
                try {
                    inetAddress = InetAddress.getLocalHost();
                    ip = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    ip = "LOCAL_HOST";
                }
            }
        } catch (Exception e) {
            ip = "UN_KNOW";
        }
        return ip;

    }

    private boolean isInsideNetwork(String ip) {
        /*
         *  判断客户单IP地址是否为内网地址
         *  内网IP网段：
         *  10.0.0.0-10.255.255.255
         *  172.16.0.0-172.31.255.255
         *  192.168.0.0-192.168.255.255
         */
        String reg = "^(192\\.168|172\\.(1[6-9]|2\\d|3[0,1]))(\\.(2[0-4]\\d|25[0-5]|[0,1]?\\d?\\d)){2}$|^10(\\.([2][0-4]\\d|25[0-5]|[0,1]?\\d?\\d)){3}$";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(ip);
        return matcher.find();
    }
}
