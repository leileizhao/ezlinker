package com.ezlinker.app.modules.email.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: ezlinker
 * @description:
 * @author: wangwenhai
 * @create: 2019-11-08 16:04
 **/
@Data
public class MailBean implements Serializable {
    private String recipient;
    private String subject;
    private String content;

}
