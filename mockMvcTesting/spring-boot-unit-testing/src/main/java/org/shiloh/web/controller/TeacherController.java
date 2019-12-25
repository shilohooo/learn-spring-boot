package org.shiloh.web.controller;

import org.shiloh.web.entity.Teacher;
import org.shiloh.web.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:41
 * @Description TODO
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public List<Teacher> list() {
        return teacherService.findAll();
    }

    @GetMapping("/info/{id}")
    public Teacher info(@PathVariable("id") Long id) {
        return teacherService.findById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return "添加教师信息成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        teacherService.delete(id);
        return String.format("已成功删除[id=%1$s]的教师", id);
    }

    @PostMapping("/update")
    public String update(@RequestBody Teacher teacher) {
        teacherService.update(teacher);
        return "修改教师信息成功";
    }
}
