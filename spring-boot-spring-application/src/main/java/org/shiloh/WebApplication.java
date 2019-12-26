package org.shiloh;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author shiloh
 **/
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        //SpringApplication application = new SpringApplication(Application.class);
        // 关闭启动logo
        //application.setBannerMode(Banner.Mode.OFF);
        // 将当前环境设为非web应用
        //application.setWebApplicationType(WebApplicationType.NONE);
        // 指定yml环境
        //application.setAdditionalProfiles("dev");
        // 启动
        //application.run(args);

        // 通过SpringApplicationBuilder API启动
        new SpringApplicationBuilder(WebApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run(args);
    }

}
