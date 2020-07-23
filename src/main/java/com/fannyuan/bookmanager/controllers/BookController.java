package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {
        loadAllBooksView(model);
        return "book/books";
    }

    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
