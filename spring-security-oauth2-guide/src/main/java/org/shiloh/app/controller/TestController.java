package org.shiloh.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:55
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public Object msg(Authentication authentication) {
        return authentication;
    }
}
