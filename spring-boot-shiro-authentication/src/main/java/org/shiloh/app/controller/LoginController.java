package org.shiloh.app.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.shiloh.app.entity.User;
import org.shiloh.app.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 17:24
 * @description
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String toLoginPage() {
        return "loginPage";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        try {
            UsernamePasswordToken token;
            if (rememberMe != null) {
                token = new UsernamePasswordToken(username, password, rememberMe);
            } else {
                token = new UsernamePasswordToken(username, password);
            }
            // 获取Subject对象
            Subject subject = SecurityUtils.getSubject();
            // 用户登录
            subject.login(token);
            return Result.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return Result.error(e.getMessage());
        } catch (AuthenticationException e) {
            return Result.error("用户身份认证失败！");
        }
    }

    @RequestMapping("/")
    public String toIndexPage() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String logged(Model model) {
        // 登录成功后，即可通过Subject对象获取已登录成功的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/403")
    public String unAuthorization() {
        return "403";
    }
}
