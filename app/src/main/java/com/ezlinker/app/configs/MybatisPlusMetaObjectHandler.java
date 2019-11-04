package com.ezlinker.app.configs;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 填充器
 *
 * @author nieqiurong 2018-08-10 22:59:23.
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        //避免使用metaObject.setValue()
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            this.setFieldValByName("createTime", now, metaObject);
        }

        Object recordVersion = this.getFieldValByName("recordVersion", metaObject);
        if (null == recordVersion) {
            this.setFieldValByName("recordVersion", 0, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
