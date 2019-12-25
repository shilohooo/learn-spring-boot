package org.shiloh.web.service.impl;

import org.shiloh.web.entity.Book;
import org.shiloh.web.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Shiloh
 * @Date 2019/11/26 15:05
 * @Description TODO
 */
@Service
public class BookServiceImpl implements BookService {

    private static final AtomicLong counter = new AtomicLong();

    /**
     * 使用集合模拟数据库
     */
    private static List<Book> books = new ArrayList<>(Arrays.asList(new Book(counter.incrementAndGet(), "book")));

    /**
     * 模拟数据库存储book信息
     */
    private static Map<String, Book> bookMap = new HashMap<>();

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book insert(Book book) {
        book.setId(bookMap.size() + 1L);
        bookMap.put(book.getId().toString(), book);
        return book;
    }

    @Override
    public Book update(Book book) {
        bookMap.put(book.getId().toString(), book);
        return book;
    }

    @Override
    public Book delete(Long id) {
        return bookMap.remove(id.toString());
    }

    @Override
    public Book findById(Long id) {
        return bookMap.get(id.toString());
    }

    @Override
    public Boolean exists(Book book) {
        return findByName(book.getName()) != null;
    }

    @Override
    public Book findByName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }
}
