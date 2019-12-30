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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

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

    @GetMapping("/image.jpg")
    public void createCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode imageCode = createImageCode();
            sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE_CODE, imageCode);
            ImageIO.write(imageCode.getImage(), "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.info("验证码生成失败：[{}]", e.getMessage());
        }
    }

    @GetMapping("/sms_code")
    public void createSmsCode(HttpServletRequest request, String mobile) {
        SmsCode smsCode = getSmsCode();
        // 将短信验证码保存到Session中，对应的key为SESSION_KEY_SMS_CODE
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE + mobile, smsCode);
        // 输出验证码到控制台代替短信发送服务
        System.out.println(String.format("您的短信验证码为：%1$s，有效时间为60秒", smsCode.getCode()));
    }

    /**
     * 获取短信验证码（生成一个6位的纯数字随机数）
     * @author lxlei
     * @return org.shiloh.app.validate.sms.code.SmsCode
     **/
    public SmsCode getSmsCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code, 60);
    }

    /**
     * 创建随机验证码
     * @author lxlei
     * @return org.shiloh.app.validate.code.ImageCode
     **/
    private ImageCode createImageCode() {
        // 验证码图片宽高，单位：px
        int width = 90;
        int height = 36;
        // 验证码个数
        int length = 4;
        // 验证码有效时间60s
        int expireIn = 60;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        Random random = new Random();
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Time New Roman", Font.ITALIC, 20));
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(11);
            graphics.drawLine(x, y, (x + xl), (y + yl));
        }
        StringBuilder builder = new StringBuilder();
        String rand;
        for (int i = 0; i < length; i++) {
            rand = String.valueOf(random.nextInt(10));
            builder.append(rand);
            graphics.setColor(new Color((20 + random.nextInt(100)), (20 + random.nextInt(110)), (20 + random.nextInt(110))));
            graphics.drawString(rand, (13 * i + 6), 16);
        }
        graphics.dispose();
        return new ImageCode(image, builder.toString(), expireIn);
    }

    /**
     * 生成随机颜色
     * @author lxlei
     * @param fc
     * @param bc
     * @return java.awt.Color
     **/
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
