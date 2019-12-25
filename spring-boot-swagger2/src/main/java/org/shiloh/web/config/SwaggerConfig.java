package org.shiloh.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author Shiloh
 * @Date 2019/11/29 12:22
 * @Description TODO
 */
@Configuration
public class SwaggerConfig {

    /**
     * swagger2 API文档配置
     * @return
     */
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.shiloh.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * swagger2 API文档基础信息
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot整合Swagger2 RESTful API文档")
                .contact(new Contact("shiloh", "https://github.com/shilohooo", "lixiaolei595@gmail.com"))
                .version("v1.0")
                .build();
    }
}
