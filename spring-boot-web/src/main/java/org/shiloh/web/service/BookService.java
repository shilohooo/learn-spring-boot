package org.shiloh.web.service;

import org.shiloh.web.entity.Book;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/26 15:01
 * @Description TODO
 */
public interface BookService {
    /**
     * 获取所有 Book
     *
     * @return
     */
    List<Book> findAll();

    /**
     * 新增 Book
     *
     * @param book {@link Book}
     * @return
     */
    Book insert(Book book);

    /**
     * 更新 Book
     *
     * @param book {@link Book}
     * @return
     */
    Book update(Book book);

    /**
     * 根据id删除 Book
     *
     * @param id
     * @return
     */
    Book delete(Long id);

    /**
     * 根据id获取 Book
     *
     * @param id
     * @return
     */
    Book findById(Long id);

    /**
     * 查找 Book 是否存在
     *
     * @param book
     * @return
     */
    Boolean exists(Book book);

    /**
     * 根据书名查找 Book
     *
     * @param name
     * @return
     */
    Book findByName(String name);
}
