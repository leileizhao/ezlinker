package com.ezlinker.app.modules.relation.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-24
 */
@Data
@Accessors(chain = true)
@TableName("ez_device_module")
public class DeviceModule   {

    private static final long serialVersionUID=1L;

    /**
     * 设备
     */
    private Long deviceId;

    /**
     *  模块
     */
    private Long moduleId;


}
