package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.service.BookService;
import com.fannyuan.bookmanager.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {
        User host = hostHolder.getUser();

        if (host != null) {
            model.addAttribute("host", host);
        }

        loadAllBooksView(model);
        return "book/books";
    }

    @RequestMapping(path = {"/books/add"}, method = {RequestMethod.POST})
    public String addBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") double price
    ) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBook(book);

        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
    public String deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBooks(bookId);
        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(@PathVariable("bookId") int bookId) {
        bookService.recoverBooks(bookId);
        return "redirect:/index";
    }

    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
