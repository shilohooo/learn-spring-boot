package org.shiloh.app.controller;

import org.shiloh.app.entity.OnlineUserInfo;
import org.shiloh.app.service.SessionService;
import org.shiloh.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 15:50
 * @description 对在线用户进行某些操作的controller
 */
@Controller
@RequestMapping("/online")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/index")
    public String online() {
        return "online";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<OnlineUserInfo> list() {
        return sessionService.getOnlineUserInfoList();
    }

    @ResponseBody
    @GetMapping("/force_logout/{id}")
    public Result forceLogout(@PathVariable("id") String id) {
        try {
            sessionService.forceLogout(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(String.format("踢出sessionId为[%1$s]的用户失败", id));
        }
    }
}
