package com.ezlinker.app.modules.dictionary.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典的值
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_dictionary_value")
public class DictionaryValue extends XEntity {

    private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String value;

    /**
     * 显示的文本
     */
    private String label;


}
