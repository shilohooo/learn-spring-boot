package org.shiloh.web.controller;

import org.shiloh.web.entity.Student;
import org.shiloh.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/28 9:38
 * @Description TODO
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.findAll();
    }

    @GetMapping("/info/{id}")
    public Student info(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Student student) {
        studentService.addStudent(student);
        return "successful!";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "successful!";
    }

    @PostMapping("/update")
    public String update(@RequestBody Student student) {
        studentService.updateStudent(student);
        return "successful!";
    }
}
