package org.shiloh.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Shiloh
 * @Date 2019/11/26 11:20
 * @Description 
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>("Hello World", httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/redirect-test")
    public void redirectTest(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8083/redirect-params?TokenAuthResponse=shiloh");
    }

    @GetMapping("/redirect-params")
    public void getRedirectParams(@RequestParam(value = "TokenAuthResponse", required = false)
                                              String tokenAuthResponse) {
        System.out.println("tokenAuthResponse = " + tokenAuthResponse);
    }
}
