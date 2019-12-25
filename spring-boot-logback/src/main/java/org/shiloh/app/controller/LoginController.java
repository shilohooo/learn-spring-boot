package org.shiloh.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 14:47
 * @description
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/msg")
    public String msg() {
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "something...";
    }
}
