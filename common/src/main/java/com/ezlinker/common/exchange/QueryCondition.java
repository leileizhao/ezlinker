package com.ezlinker.common.exchange;

import lombok.Data;

/**
 * @program: ezlinker
 * @description: 查询条件封装
 * @author: wangwenhai
 * @create: 2019-11-04 18:04
 **/
@Data
public class QueryCondition<T> {
    private T query;
}
