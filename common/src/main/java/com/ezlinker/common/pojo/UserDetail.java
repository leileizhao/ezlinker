package com.ezlinker.common.pojo;

import lombok.Data;

/**
 * @program: ezlinker
 * @description: Token解析出来的User数据
 * @author: wangwenhai
 * @create: 2019-11-05 15:42
 **/
@Data
public class UserDetail {
    private Long id;
    private String username;
    private Integer userType;
    private Integer status;
    private Long expiredTime;

}
