package org.shiloh.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shiloh
 * @Date 2019/11/26 11:20
 * @Description TODO
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>("Hello World", httpHeaders, HttpStatus.OK);
    }
}
