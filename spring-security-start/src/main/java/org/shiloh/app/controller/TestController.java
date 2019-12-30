package org.shiloh.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 21:28
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/msg")
    public String msg() {
        return "Hello Spring Security:)";
    }

    /*@GetMapping("/index")
    public Object toIndexPage() {
        return SecurityContextHolder.getContext().getAuthentication();
    }*/

    @GetMapping("/index")
    public Object toIndexPage(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/session_invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {
        return "当前会话已过期，请重新认证~";
    }
}
