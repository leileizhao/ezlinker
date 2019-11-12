package com.ezlinker.app.modules.userlog.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录日志
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_user_login_log")
public class UserLoginLog extends XEntity {

    private static final long serialVersionUID=1L;

    private Long userId;

    private String status;

    private String ip;

    private String remark;

    private String location;


}
