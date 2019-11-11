package com.ezlinker.app.modules.email.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ezlinker.app.configs.AliyunMailProperties;
import com.ezlinker.common.exception.XException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: ezlinker
 * @description: 邮件发送
 * @author: wangwenhai
 * @create: 2019-11-08 16:03
 **/
@Service
public class MailService {
    @Resource
    AliyunMailProperties aliyunMailProperties;

    public void sendTextMail(String email, String subject, String content) throws XException {
        IClientProfile profile = DefaultProfile.getProfile(aliyunMailProperties.getRegionId(),
                aliyunMailProperties.getAccessKey(),
                aliyunMailProperties.getSecret());

        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName(aliyunMailProperties.getAccountName());
        request.setFromAlias(aliyunMailProperties.getFromAlias());
        request.setAddressType(aliyunMailProperties.getAddressType());
        request.setTagName(aliyunMailProperties.getTagName());
        request.setReplyToAddress(true);
        request.setToAddress(email);
        request.setSubject(subject);
        request.setHtmlBody(content);
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new XException(501, "Email send failure", "邮件发送失败,请联系管理员.");
        }
    }

}
