package com.ezlinker.app.modules.email.service;

import com.ezlinker.app.modules.email.model.MailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @program: ezlinker
 * @description: 邮件发送
 * @author: wangwenhai
 * @create: 2019-11-08 16:03
 **/
@Service
public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailBean
     */
    public void sendHTMLMail(MailBean mailBean) {
        try {
            MimeMessage mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom("751957846@qq.com");
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent(), true);
            mailSender.send(mimeMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
