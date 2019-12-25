package org.shiloh.web.controller;

import org.shiloh.web.config.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shiloh
 * @Date 2019/11/26 11:57
 * @Description TODO
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookProperties bookProperties;

    @GetMapping("/info")
    public String getBookInfo() {
        return String.format("Hello,%1$s is writing %2$s !", bookProperties.getWriter(), bookProperties.getName());
    }
}
