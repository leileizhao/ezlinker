package com.ezlinker.app.modules.rolepermission.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和权限关联表
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_role_permission")
public class RolePermission extends XEntity {

    private static final long serialVersionUID=1L;

    private Integer roleId;

    private Integer permissionId;


}
