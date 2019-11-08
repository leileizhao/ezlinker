package com.ezlinker.app.modules.captcha;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.utils.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: ezlinker
 * @description: 验证码生成器
 * @author: wangwenhai
 * @create: 2019-11-08 14:30
 **/
@RestController
public class CaptchaController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/captcha")
    public R captcha() throws Exception {
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 5);
        captcha.setFont(Captcha.FONT_9);
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);

        String code = captcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为1分钟
        try {
            Boolean set = redisUtil.set("CAPTCHA:" + code, key, 60);
            if (set) {
                System.out.println("Key:" + key + " Code:" + code);
                return new R(200, "Captcha generate success", "验证码获取成功", captcha.toBase64());

            } else {
                throw new XException(500, "Internal error,Maybe redis has downed", "系统内部错误");
            }

        } catch (Exception e) {
            throw new XException(500, "Internal error,Maybe redis has downed", "系统内部错误");
        }

    }

    boolean checkCaptcha(String code) {
        String uuid = redisUtil.get("CAPTCHA:" + code).toString();
        return uuid != null;

    }
}
