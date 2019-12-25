package org.shiloh.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author Shiloh
 * @Date 2019/11/26 11:55
 * @Description TODO
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo.book")
@Validated
public class BookComponent {
    @NotEmpty
    private String name;

    @NotNull
    private String writer;
}
