package org.shiloh.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 11:53
 * @description
 */
@Controller
public class TestController {
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * @CrossOrigin 跨域支持
     * @return java.lang.String
     **/
    @RequestMapping("/hello")
    @ResponseBody
    //@CrossOrigin(value = "*")
    public String toHello() {
        return "hello";
    }
}
