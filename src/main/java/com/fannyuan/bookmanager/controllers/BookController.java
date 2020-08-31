package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.controllers.request.NewBook;
import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.BookService;
import com.fannyuan.bookmanager.service.HostHolder;
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

    @Resource
    private HostHolder hostHolder;

    @GetMapping("/index")
    public ResponseEntity<List<Book>> bookList() {
        User host = hostHolder.getUser();
        List<Book> books = null;

        HttpStatus status;
        if (host != null) {
            books = bookService.getAllBooks();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(books, status);
    }

    @PostMapping(path = "/books/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public int addBook(@RequestBody NewBook newBook) {
        Book book = new Book();
        book.setName(newBook.getName());
        book.setAuthor(newBook.getAuthor());
        book.setPrice(newBook.getPrice());
        return bookService.addBook(book);
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBooks(bookId);
    }

    @GetMapping(path = "/books/{bookId:[0-9]+}/recover")
    @ResponseStatus(HttpStatus.OK)
    public void recoverBook(@PathVariable("bookId") int bookId) {
        bookService.recoverBooks(bookId);
    }
}
