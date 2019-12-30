package org.shiloh.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.shiloh.app.validate.code.ImageCode;
import org.shiloh.app.validate.sms.code.SmsCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 9:17
 * @description 生成图片验证码
 */
@RestController
@RequestMapping("/code")
@Slf4j
public class ValidateController {

    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/normal")
    public String createCode(HttpServletRequest request) {
        ImageCode imageCode = new ImageCode(getTestVerifyCode(), 60);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE_CODE, imageCode);
        return imageCode.getCode();
    }

    @GetMapping("/sms_code")
    public void createSmsCode(HttpServletRequest request, String mobile) {
        SmsCode smsCode = new SmsCode(getTestVerifyCode(), 60);
        // 将短信验证码保存到Session中，对应的key为SESSION_KEY_SMS_CODE
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE + mobile, smsCode);
        // 输出验证码到控制台代替短信发送服务
        System.out.println(String.format("您的短信验证码为：%1$s，有效时间为60秒", smsCode.getCode()));
    }

    /**
     * 获取测试验证码（生成一个6位的纯数字随机数）
     * @author lxlei
     * @return org.shiloh.app.validate.sms.code.SmsCode
     **/
    public String getTestVerifyCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}
