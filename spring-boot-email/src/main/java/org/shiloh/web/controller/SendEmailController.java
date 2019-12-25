package org.shiloh.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author Shiloh
 * @Date 2019/11/29 16:42
 * @Description TODO
 */
@RequestMapping("/send-email")
@RestController
public class SendEmailController {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine engine;

    private static final String TO = "shiloh595@163.com";

    /**
     * 发送一封简单的纯文本邮件
     * @return
     */
    @GetMapping("/simple")
    public String sendSimpleEmail() {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(TO);// 接收地址
            mailMessage.setSubject("一封简单的测试邮件");// 邮件标题
            mailMessage.setText("Hello World:p, 使用SpringBoot整合JavaMail发送邮件~~");// 邮件内容
            sender.send(mailMessage);
            return "发送邮件成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("发送邮件失败：%1$s", e.getMessage());
        }
    }

    /**
     * 发送HTML格式的邮件
     * @return
     */
    @GetMapping("/html-email")
    public String sendHtmlEmail() {
        MimeMessage message;
        try {
            message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("一封HTML格式的邮件");
            // 带HTML格式的内容
            helper.setText("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>", true);// true表示发送HTML格式邮件
            sender.send(message);
            return "已成功发送HTML格式的邮件";
        } catch (MessagingException e) {
            e.printStackTrace();
            return String.format("发送邮件失败：%1$s", e.getMessage());
        }
    }

    /**
     * 发送带有附件的邮件
     * @return
     */
    @GetMapping("/attachment-email")
    public String sendEmailWithAttachment() {
        MimeMessage message;
        try {
            message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("一封带有附件的邮件:)");
            helper.setText("详情请参阅附件内容！");
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("D:\\test1.sql"));
            helper.addAttachment("test1.sql", file);
            sender.send(message);
            return "已成功发送带有附件的邮件";
        } catch (MessagingException e) {
            e.printStackTrace();
            return String.format("发送邮件失败：%1$s", e.getMessage());
        }
    }

    /**
     * 发送一封带静态资源的邮件
     * 这里发送带图片的
     *
     * 发送带静态资源的邮件其实就是在发送HTML邮件的基础上嵌入静态资源（比如图片），
     * 嵌入静态资源的过程和传入附件类似，唯一的区别在于需要标识资源的cid：
     * @return
     */
    @GetMapping("/img-email")
    public String sendEmailWithImage() {
        MimeMessage message;
        try {
            message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("一封带有静态资源的邮件:-)");
            helper.setText("<html><body>测试图片：<img src='cid:img'/></body></html>", true); // 内容
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("D:\\luffy.png"));
            // helper.addInline("img", file);中的img和图片标签里cid后的名称相对应
            helper.addInline("img", file);
            sender.send(message);
            return "已成功发送带图片的邮件";
        } catch (MessagingException e) {
            e.printStackTrace();
            return String.format("发送邮件失败：%1$s", e.getMessage());
        }
    }

    /**
     * 使用模板发送邮件
     * 在发送验证码等情况下可以创建一个邮件的模板，唯一的变量为验证码。
     * 这个例子中使用的模板解析引擎为Thymeleaf
     */
    @GetMapping("/template-email/{code}")
    public String sendEmailUseTemplate(@PathVariable("code") String code) {
        MimeMessage message;
        try {
            message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("一封使用邮件模板发送的验证码邮件:p");
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", code);
            String template = engine.process("email-template", context);
            helper.setText(template, true);
            sender.send(message);
            return "使用模板发送验证码邮件成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return String.format("发送邮件失败：%1$s", e.getMessage());
        }
    }
}
