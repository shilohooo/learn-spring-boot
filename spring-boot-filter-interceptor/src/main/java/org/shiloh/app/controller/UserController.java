package org.shiloh.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:05
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/msg")
    public String msg() {
        throw new RuntimeException("no response data:(");
    }
}
