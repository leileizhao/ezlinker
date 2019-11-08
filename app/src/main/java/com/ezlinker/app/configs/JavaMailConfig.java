package com.ezlinker.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @program: ezlinker
 * @description: 邮件发送
 * @author: wangwenhai
 * @create: 2019-11-08 16:20
 **/
@Configuration
public class JavaMailConfig {
    /**
     * host: smtp.qq.com
     * username: 751957846@qq.com
     * password: oqozfmqqhlzubdih
     * default-encoding: UTF-8
     *     properties:
     *       mail:
     *         smtp:
     *           auth: true
     *           starttls:
     *             enable: true
     *             required: true
     * QQ邮箱 POP3 和 SMTP 服务器地址设置如下：
     *
     * 邮箱	    POP3服务器（端口995）	SMTP服务器（端口465或587）
     * qq.com	pop.qq.com	        smtp.qq.com

     * @return
     */

    @Bean
    JavaMailSenderImpl javaMailSenderImp() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername("751957846@qq.com");
        javaMailSender.setPassword("oqozfmqqhlzubdih");
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setPort(587);

        return javaMailSender;
    }
}
