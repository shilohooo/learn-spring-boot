package org.shiloh.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/26 14:59
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private Long id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String writer;

    /**
     * 简介
     */
    private String introduction;

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name) {
        this.name = name;
    }
}
