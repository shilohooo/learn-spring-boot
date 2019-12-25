package org.shiloh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.web.entity.Student;
import org.shiloh.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01() {
        Student studentA = studentService.get(1L);
        System.out.println("studentA.getName() = " + studentA.getName());

        Student studentB = studentService.get(1L);
        System.out.println("studentB.getName() = " + studentB.getName());
    }

    @Test
    public void test02() {
        // 设置一对key/value
        redisTemplate.opsForValue().set("age", 21);
        // 根据key获取value值
        Integer age = (Integer) redisTemplate.opsForValue().get("age");
        System.out.println("age = " + age);
    }

}
