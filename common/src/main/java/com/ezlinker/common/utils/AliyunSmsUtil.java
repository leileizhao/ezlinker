package com.ezlinker.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ezlinker.common.exception.XException;

/**
 * @program: ezlinker
 * @description: 阿里云短信发送工具类
 * @author: wangwenhai
 * @create: 2019-09-12 18:06
 **/
public class AliyunSmsUtil {
    /**
     * 获取一个随机数
     *
     * @return
     */
    public static int getCode() {
        int max = 999999, min = 100000;
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param code
     */
    public static boolean sendSms(String phone, int code) throws XException {
        return sendSms(phone, code, "SMS_172805274");
    }

    /**
     * 发送找回密码的短信
     *
     * @param phone
     * @param code
     * @return
     */
    public static boolean sendResetPasswordSms(String phone, int code) throws XException {
        return sendSms(phone, code, "SMS_172805273");
    }

    private static boolean sendSms(String phone, int code, String template) throws XException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FidL4WMDDSUY3t9nHJ5", "KAQ4fIhCgW9T4B63Unm0jyXBwadAaV");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "中科商企");
        request.putQueryParameter("TemplateCode", template);
        JSONObject param = new JSONObject();
        param.put("code", code);
        request.putQueryParameter("TemplateParam", param.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject result = JSONObject.parseObject(response.getData());
            return result.getString("Code").equals("OK");
        } catch (ClientException e) {
            throw new XException(1, "SMS send failure", "短信发送失败");

        }
    }


    public static void main(String[] args) throws XException {
        AliyunSmsUtil.sendResetPasswordSms("18059150204", getCode());
    }
}
