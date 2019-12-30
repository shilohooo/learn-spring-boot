package org.shiloh.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 21:28
 * @description
 */
@Controller
public class TestController {

    @GetMapping("/msg")
    @ResponseBody
    public String msg() {
        return "Hello Spring Security:)";
    }

    /*@GetMapping("/index")
    public Object toIndexPage() {
        return SecurityContextHolder.getContext().getAuthentication();
    }*/

    @GetMapping("/index")
    @ResponseBody
    public Object toIndexPage(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/session_invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String sessionInvalid() {
        return "当前会话已过期，请重新认证~";
    }

    @GetMapping("/to/sms_login_page")
    public String toSmsLoginPage() {
        return "mobile_login";
    }
}
