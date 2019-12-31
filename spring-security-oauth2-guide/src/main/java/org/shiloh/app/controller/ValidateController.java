package org.shiloh.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.shiloh.app.common.service.RedisCodeService;
import org.shiloh.app.validate.smscode.SmsCode;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RedisCodeService redisCodeService;

    @GetMapping("/sms_code")
    public void createSmsCode(HttpServletRequest request, String mobile) {
        SmsCode smsCode = createSmsCode();
        // 将短信验证码保存到redis中
        redisCodeService.save(smsCode, new ServletWebRequest(request), mobile);
        // 输出验证码到控制台代替短信发送服务
        System.out.println(String.format("您的短信验证码为：%1$s，有效时间为5分钟", smsCode.getCode()));
    }

    /**
     * 获取测试验证码（生成一个6位的纯数字随机数）
     * @author lxlei
     * @return org.shiloh.app.validate.sms.code.SmsCode
     **/
    public SmsCode createSmsCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code);
    }
}
