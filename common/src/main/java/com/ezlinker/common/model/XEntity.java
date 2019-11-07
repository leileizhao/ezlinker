package com.ezlinker.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @program: ezlinker
 * @description: 基础表
 * @author: wangwenhai
 * @create: 2019-11-04 17:11
 **/
@Data
public class XEntity {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 删除标记
     */
    @JsonIgnore
    private Boolean x = false;
    /**
     * 乐观锁
     */
    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE, update = "%s+1")
    @JsonIgnore
    private Integer recordVersion;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime = new Date();

}
