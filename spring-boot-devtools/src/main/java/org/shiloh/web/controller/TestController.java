package org.shiloh.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shiloh
 * @Date 2019/12/2 9:05
 * @Description TODO
 */
@RestController
public class TestController {

    @GetMapping("/msg")
    public String msg() {
        return "Hello World!";
    }
}
