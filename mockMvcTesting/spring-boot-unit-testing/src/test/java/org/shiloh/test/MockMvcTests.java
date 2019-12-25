package org.shiloh.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.web.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:27
 * @Description TODO MockMvc模拟请求测试TeacherController中的方法
 */
@SpringBootTest
@RunWith(SpringRunner.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class MockMvcTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    /**
     * MockMvc，从字面上来看指的是模拟的MVC，即其可以模拟一个MVC环境，向Controller发送请求然后得到响应。
     * 在单元测试中，使用MockMvc前需要进行初始化。
     */
    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 模拟get请求测试获取所有teacher
     */
    @Test
    public void testFindAllTeachers() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/teacher/list"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟get请求测试根据id获取某个teacher
     */
    @Test
    public void testFindTeacherById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/teacher/info/{id}", 1)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟post请求测试添加一个teacher
     */
    @Test
    public void testAddTeacher() {
        try {
            Teacher teacher = new Teacher();
            teacher.setName("mark")
                    .setGender(1)
                    .setAge(23);
            String jsonData = mapper.writeValueAsString(teacher);
            mockMvc.perform(MockMvcRequestBuilders.post("/teacher/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonData.getBytes()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟get请求测试根据id删除某个teacher
     */
    @Test
    public void testDeleteTeacher() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/teacher/delete/{id}", 2)
                    .contentType(MediaType.TEXT_PLAIN))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟post请求测试修改某个teacher的信息
     */
    @Test
    public void testUpdateTeacher() {
        try {
            Teacher teacher = new Teacher();
            teacher.setName("mark123")
                    .setGender(1)
                    .setAge(22)
                    .setId(4L);
            String jsonData = mapper.writeValueAsString(teacher);
            mockMvc.perform(MockMvcRequestBuilders.post("/teacher/update")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(jsonData.getBytes()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
