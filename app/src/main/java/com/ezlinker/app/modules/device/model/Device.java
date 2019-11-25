package com.ezlinker.app.modules.device.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 实际设备，是产品的一个实例。
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "ez_device",autoResultMap = true)
public class Device extends XEntity {

    private static final long serialVersionUID=1L;

    /**
     * 项目
     */
    @NotNull(message = "必须指定所属项目")
    private Integer projectId;

    /**
     * 产品
     */
    @NotNull(message = "必须指定所属产品")
    private Integer productId;

    /**
     * 名称
     */
    @NotEmpty(message = "必须指定设备名称")
    private String name;

    /**
     * Logo
     */
    private String logo;

    /**
     * 地理位置
     */
    private String location;

    /**
     * 型号
     */
    private String model;

    /**
     * 标签
     */
    @TableField(exist = false)
    private String[] tags;

    /**
     * 厂家
     */
    private String industry;

    /**
     * 序列号
     */
    private String sn;

    /**
     * 类型
     */
    private String type;

    /**
     * 参数
     */

    @TableField(typeHandler = JacksonTypeHandler.class)

    private List<HashMap<String, Object>> parameter;


    /**
     * 描述
     */
    private String description;

}
