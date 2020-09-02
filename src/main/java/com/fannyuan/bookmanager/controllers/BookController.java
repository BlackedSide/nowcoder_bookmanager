package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.service.BookService;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/index")
    public ResponseEntity<List<Book>> bookList() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(path = "/books/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        Book book = new Book();
        book.setName(newBook.getName());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());
        book.setRemains(newBook.getRemains());
        DateTime date = new DateTime();
        book.setCreateTime(date.toDate());
        book.setUpdateTime(date.toDate());
        int status = bookService.addBook(book);
        if (status > 0) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/delete")
    public ResponseEntity<Book> deleteBook(@PathVariable("bookId") int bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null && book.getStatus() != 1) {
            bookService.deleteBooks(bookId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/recover")
    public ResponseEntity<Book> recoverBook(@PathVariable("bookId") int bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null && book.getStatus() != 0) {
            bookService.recoverBooks(bookId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        }
    }
}
