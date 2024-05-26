package org.shiloh.app.controller;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:55
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public Object msg(@AuthenticationPrincipal Authentication authentication,
                      HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        System.out.println("header = " + header);
        final String token = StringUtils.substringAfter(header, "bearer ");
        System.out.println("token = " + token);
        return Jwts.parser()
                .setSigningKey("shiloh".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
