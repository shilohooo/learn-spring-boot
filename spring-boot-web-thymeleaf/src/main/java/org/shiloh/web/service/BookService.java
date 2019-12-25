package org.shiloh.web.service;

import org.shiloh.web.entity.Book;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/27 9:24
 * @Description TODO
 */
public interface BookService {
    List<Book> findAll();

    Book save(Book book);

    Book update(Book book);
}
