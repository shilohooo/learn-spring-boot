package org.shiloh.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lxlei
 * @date Created in 2020/1/16 10:21
 * @description
 */
@RestController
public class UserController {

    @GetMapping("/info")
    public Principal info(Principal principal) {
        return principal;
    }

    @GetMapping("/auth/add")
    @PreAuthorize("hasAuthority('user:add')")
    public String authorizationAddPermission() {
        return "您拥有[user:add]权限";
    }

    @GetMapping("/auth/update")
    @PreAuthorize("hasAuthority('user:update')")
    public String authorizationUpdatePermission() {
        return "您拥有[user:update]权限";
    }

}
