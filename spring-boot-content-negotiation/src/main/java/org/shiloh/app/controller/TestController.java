package org.shiloh.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 10:47
 * @description
 */
//@RestController
@Controller
public class TestController {
    @GetMapping(value = "/test1", consumes = "text/properties")
    @ResponseBody
    public Properties test01(@RequestBody Properties properties) {
        return properties;
    }

    @GetMapping(value = "/test2", consumes = "text/properties")
    //@ResponseBody
    public Properties test02(Properties properties) {
        return properties;
    }
}
