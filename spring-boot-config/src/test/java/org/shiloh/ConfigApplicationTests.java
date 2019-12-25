package org.shiloh;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.web.config.BookComponent;
import org.shiloh.web.config.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigApplicationTests {
    @Autowired
    private BookProperties bookProperties;

    @Autowired
    private BookComponent bookComponent;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testBookProperties() {
        Assert.assertEquals(bookProperties.getName(), "《追风筝的人》");
        Assert.assertEquals(bookProperties.getWriter(), "卡勒德·胡塞尼");
    }

    // spring.profiles.active = dev
    @Test
    public void testBookComponent() {
        Assert.assertEquals(bookComponent.getName(), "《追风筝的人》from dev");
        Assert.assertEquals(bookComponent.getWriter(), "卡勒德·胡塞尼");
    }
}
