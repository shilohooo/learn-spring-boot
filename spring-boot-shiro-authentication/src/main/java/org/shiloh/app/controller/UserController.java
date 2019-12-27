package org.shiloh.app.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 12:20
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequiresPermissions("user:user")
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("value", "获取用户信息列表");
        return "user";
    }

    @RequiresPermissions("user:save")
    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }
}
