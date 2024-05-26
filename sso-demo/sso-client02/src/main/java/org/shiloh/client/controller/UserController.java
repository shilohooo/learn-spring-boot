package org.shiloh.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxlei
 * @date Created in 2020/1/16 10:24
 * @description
 */
@RestController
public class UserController {

    @GetMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }
}
