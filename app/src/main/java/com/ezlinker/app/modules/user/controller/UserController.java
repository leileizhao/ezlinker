package com.ezlinker.app.modules.user.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.user.form.ResetPasswordForm;
import com.ezlinker.app.modules.user.form.UserUpdateForm;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.app.modules.userlog.model.UserLoginLog;
import com.ezlinker.app.modules.userlog.service.IUserLoginLogService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.utils.RegxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/users")
public class UserController extends AbstractXController<User> {

    @Autowired
    IUserService iUserService;

    @Autowired
    IUserLoginLogService iUserLoginLogService;

    public UserController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }


    /**
     * 获取用户的操作菜单
     *
     * @return
     * @throws XException
     */
    @GetMapping("/menu")
    public R getMenu() throws XException {
        return data(iUserService.getAllPermissions(getUserDetail().getId()));
    }

    /**
     * 获取用户详情
     *
     * @return
     * @throws XException
     */
    @GetMapping("/userInfo")
    public R getUserInfo() throws XException {
        return data(iUserService.getUserInfo(getUserDetail().getId()));
    }

    /**
     * 获取用户的登录日志
     *
     * @return
     * @throws XException
     */
    @GetMapping("/loginLog")
    public R getLoginLog(@RequestParam int pageNo, @RequestParam int pageSize) throws XException {
        QueryWrapper<UserLoginLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", getUserDetail().getId());
        IPage<UserLoginLog> page = iUserLoginLogService.page(new Page<>(pageNo, pageSize), queryWrapper);
        return data(page);
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PutMapping("/resetPassword")
    public R resetPassword(@RequestBody ResetPasswordForm form) throws XException {
        if (!form.getNewPassword().equals(form.getPasswordRetry())) {
            throw new XException("Invalid password!", "两次密码不一致");
        }
        User user = iUserService.getById(getUserDetail().getId());
        if (!user.getPassword().toUpperCase().equals(SecureUtil.md5(form.getNewPassword()))) {
            throw new XException("Invalid password!", "旧密码错误");
        }
        user.setPassword(SecureUtil.md5(form.getNewPassword()));
        boolean ok = iUserService.updateById(user);
        return ok ? success() : fail();

    }

    /**
     * 更新用户信息
     *
     * @param form
     * @return
     * @throws XException
     */
    @PutMapping("/info")
    public R updateInfo(UserUpdateForm form) throws XException {
        User user = iUserService.getById(getUserDetail().getId());
        if (!StringUtils.isEmpty(form.getPhone())) {
            if (!RegxUtil.isPhone(form.getPhone())) {
                throw new XException("Invalid phone format!", "手机号码错误");

            } else {
                user.setPhone(form.getPhone());

            }

        }
        if (!StringUtils.isEmpty(form.getRealName())) {
            user.setRealName(form.getRealName());

        }
        if (!StringUtils.isEmpty(form.getNickName())) {
            user.setNickName(form.getNickName());

        }
        if (!StringUtils.isEmpty(form.getEmail())) {
            if (!RegxUtil.isEmail(form.getEmail())) {
                throw new XException("Invalid email format!", "邮箱格式错误");
            } else {
                user.setEmail(form.getEmail());

            }
        }
        boolean ok = iUserService.updateById(user);
        return ok ? success() : fail();

    }
}

