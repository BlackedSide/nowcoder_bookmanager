package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.controllers.request.NewBook;
import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.BookService;
import com.fannyuan.bookmanager.service.HostHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    @Resource
    private BookService bookService;

    @Resource
    private HostHolder hostHolder;

    @GetMapping("/index")
    public List<Book> bookList() {
        User host = hostHolder.getUser();

        if (host != null) {
            return bookService.getAllBooks();
        } else {
            return null;
        }
    }

    @PostMapping(path = "/books/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpStatus addBook(@RequestBody NewBook newBook) {
        Book book = new Book();
        book.setName(newBook.getName());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());
        int status = bookService.addBook(book);
        if (status > 0) {
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/delete")
    public HttpStatus deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBooks(bookId);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/recover")
    public HttpStatus recoverBook(@PathVariable("bookId") int bookId) {
        bookService.recoverBooks(bookId);
        return HttpStatus.OK;
    }
}
