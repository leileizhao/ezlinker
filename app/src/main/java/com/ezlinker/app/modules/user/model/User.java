package com.ezlinker.app.modules.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_user")
public class User extends XEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 实名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型：普通用户【1】，企业用户【2】，VIP用户【3】
     */
    private Integer userType;

    /**
     * 账户状态：正常【1】，冻结【2】，过期【3】
     */
    private Integer status;

    /**
     * 扩展信息
     */
    private Integer userProfileId;

    /**
     * 上次登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 上次登录IP
     */
    private String lastLoginIp;


}
