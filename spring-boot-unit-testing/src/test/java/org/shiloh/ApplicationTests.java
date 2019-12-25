package org.shiloh;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {
    /**
     * JUnit4中包含了几个比较重要的注解：@BeforeClass、@AfterClass、@Before、@After和@Test。
     * 其中， @BeforeClass和@AfterClass在每个类加载的开始和结束时运行，必须为静态方法；
     * 而@Before和@After则在每个测试方法开始之前和结束之后运行。
     */

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("ApplicationTests.beforeClassTest");
    }

    @Before
    public void beforeTest() {
        System.out.println("ApplicationTests.beforeTest");
    }

    @Test
    public void test01() {
        System.out.println("test 1 + 1 = 2");
        Assert.assertEquals(2, (1 + 1));
    }

    @Test
    public void test02() {
        System.out.println("test 2 + 2 = 4");
        Assert.assertEquals(4, (2 + 2));
    }

    @After
    public void afterTest() {
        System.out.println("ApplicationTests.afterTest");
    }

    @AfterClass
    public static void afterClassTest() {
        System.out.println("ApplicationTests.afterClassTest");
    }
}
