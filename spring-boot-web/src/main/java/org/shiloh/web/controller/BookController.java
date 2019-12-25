package org.shiloh.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.shiloh.web.entity.Book;
import org.shiloh.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/26 15:13
 * @Description TODO
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取 book列表
     * @return
     */
    @GetMapping("/list")
    public List<Book> list() {
        return bookService.findAll();
    }

    /**
     * 根据id获取book信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Book info(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    /**
     * 新增book
     * @param book
     * @param builder
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Void> addBook(@RequestBody Book book, UriComponentsBuilder builder) {
        log.error("save a new book: {}", book);
        if (book.getName().equals("conflict")) {
            log.error("a book with name " + book.getName() + " is already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        bookService.insert(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(builder.path("/book/info/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 更新book
     * @param book
     * @return
     */
    @PostMapping("/update")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    /**
     * 根据id删除book
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Book delete(@PathVariable("id") Long id) {
        return bookService.delete(id);
    }
}
