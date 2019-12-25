package org.shiloh.web.controller;

import org.shiloh.web.entity.User;
import org.shiloh.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/26 17:50
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/info/{id}")
    public User info(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody @Valid User user) {
        userService.save(user);
        return "saved successfully";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "successful deleted";
    }

    @PostMapping("/update")
    public String update(@RequestBody @Valid User user) {
        userService.update(user);
        return "update successful";
    }
}
