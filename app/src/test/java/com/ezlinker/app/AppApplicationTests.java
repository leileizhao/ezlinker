package com.ezlinker.app;


import cn.hutool.crypto.SecureUtil;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.utils.AliyunEmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class AppApplicationTests {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    AliyunEmailUtil aliyunEmailUtil;
//
    @Test
    void sendTemplateMail() throws XException {
        //创建邮件正文
        Context context = new Context();

        context.setVariable("address", "1号车间");
        context.setVariable("name", "安全控制器");

        String emailContent = templateEngine.process("warning", context);
        aliyunEmailUtil.sendHtmlMail("751957846@qq.com","警告信息",emailContent);
        System.out.println("邮件发送报告测试");
    }
    @Autowired
    IUserService iUserService;

    @Test
    void addUser() {

        User user = new User();
        user.setUsername("ezlinker")
                .setPassword(SecureUtil.md5("password").toUpperCase())
                .setAvatar("ezlinker.png")
                .setEmail("751957846@qq.com")
                .setNickName("EZ-Linker-Big-dick")
                .setPhone("18059150204")
                .setStatus(1)
                .setUserType(1)
                .setUserProfileId(0);
        iUserService.save(user);
    }
}
