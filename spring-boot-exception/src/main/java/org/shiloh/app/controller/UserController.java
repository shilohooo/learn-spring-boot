package org.shiloh.app.controller;

import org.shiloh.app.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 15:32
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info/{id}")
    public void info(@PathVariable("id") Long id) {
        throw new UserNotExistsException(id);
    }
}
