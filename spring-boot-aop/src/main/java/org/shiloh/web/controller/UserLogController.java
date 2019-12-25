package org.shiloh.web.controller;

import org.shiloh.web.common.annotation.UserLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shiloh
 * @Date 2019/11/28 12:07
 * @Description TODO
 */
@RestController
@RequestMapping("/user-log")
public class UserLogController {

    @UserLog("执行方法一")
    @GetMapping("/one")
    public String test1(@RequestParam("username") String username) {
        return "method: test1";
    }

    @UserLog("执行方法二")
    @GetMapping("/two")
    public String test2() throws InterruptedException {
        Thread.sleep(2000);
        return "method: test2";
    }

    @UserLog("执行方法三")
    @GetMapping("/three")
    public String test3(@RequestParam("username") String username, @RequestParam("age") String age) {
        return "method: test3";
    }
}
