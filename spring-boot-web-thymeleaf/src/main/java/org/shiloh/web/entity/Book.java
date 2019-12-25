package org.shiloh.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/27 9:23
 * @Description TODO
 */
@Data
public class Book implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介
     */
    private String introduction;
}
