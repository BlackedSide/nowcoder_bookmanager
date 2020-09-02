package com.fannyuan.bookmanager.service;

import com.fannyuan.bookmanager.dao.BookDao;
import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.enums.BookStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.selectAll();
    }

    public Book getBookById(int id) {
        return bookDao.selectById(id);
    }

    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    public void deleteBooks(int id) {
        bookDao.updateBookStatus(id, BookStatus.DELETED.getValue());
    }

    public void recoverBooks(int id) {
        bookDao.updateBookStatus(id, BookStatus.NORMAL.getValue());
    }
}
