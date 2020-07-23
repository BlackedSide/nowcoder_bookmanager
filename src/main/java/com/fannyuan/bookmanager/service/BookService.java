package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.BookDAO;
import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.enums.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.selectAll();
    }

    public int addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public void deleteBooks(int id) {
        bookDAO.updateBookStatus(id, BookStatus.DELETE.getValue());
    }

    public void recoverBooks(int id) {
        bookDAO.updateBookStatus(id, BookStatus.NORMAL.getValue());
    }
}
