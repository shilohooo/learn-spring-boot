package org.shiloh.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Shiloh
 * @Date 2019/11/26 11:53
 * @Description TODO
 */
@Data
@Component
public class BookProperties {
    @Value("${demo.book.name}")
    private String name;

    @Value("${demo.book.writer}")
    private String writer;
}
