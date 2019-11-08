package com.ezlinker.app.modules.email.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Service;

/**
 * @program: ezlinker
 * @description: 邮件发送
 * @author: wangwenhai
 * @create: 2019-11-08 16:03
 **/
@Service
public class MailService {
    public void sendTextMail(String email) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "ULw8nLnKBktSbOhI", "hgAj2F2KtaMvLuXZzpVyVWZ0rl4SXR");

        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("ezlinker@ezlinker.cn");
            request.setFromAlias("EZLinker");
            request.setAddressType(1);
            request.setTagName("EZLinker");
            request.setReplyToAddress(true);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            request.setToAddress(email);
            request.setSubject("验证码");
            request.setHtmlBody("邮件正文");

            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        } catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
    }

}
