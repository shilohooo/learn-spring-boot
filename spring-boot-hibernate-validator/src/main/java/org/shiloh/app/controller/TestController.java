package org.shiloh.app.controller;

import org.shiloh.app.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:46
 * @description
 */
@RestController
@Validated
public class TestController {
    @GetMapping("/test1")
    public String test01(@NotBlank(message = "{required}") String username,
                         @Email(message = "{invalid}") String email) {
        return "successful";
    }

    @GetMapping("/test2")
    public String test02(@Valid User user) {
        return "successful";
    }
}
