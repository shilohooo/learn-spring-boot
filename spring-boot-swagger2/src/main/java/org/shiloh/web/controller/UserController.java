package org.shiloh.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.shiloh.web.entity.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Shiloh
 * @Date 2019/11/29 12:30
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息操作controller") // 描述此控制器的作用
public class UserController {

    @ApiIgnore
    @GetMapping("/msg")
    public String getMsg() {
        return "Hello World!";
    }

    @ApiOperation("获取单个用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/info/{id}")
    public User info(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id)
                .setUsername("shiloh")
                .setAge(21)
                .setGender("male")
                .setAddress("china")
                .setEmail("lxl@qq.com");
        return user;
    }

    @ApiOperation("获取所有用户信息列表")
    @GetMapping("/list")
    public List<User> list() {
        List<User> users = new ArrayList<>();
        for (long i = 0L; i <= 5L; i++) {
            User user = new User();
            user.setId(i)
                    .setUsername("shiloh" + i)
                    .setAge(21)
                    .setGender("male")
                    .setAddress("china")
                    .setEmail(i + "lxl@qq.com");
            users.add(user);
        }
        return users;
    }

    @ApiOperation("新增用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody User user) {
        user.setId(1L);
        Map<String, Object> result = new HashMap<>();
        result.put("params", user);
        result.put("result", "successful");
        return result;
    }

    @ApiOperation("根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return "删除 id = " + id + " 的user 成功";
    }
}
