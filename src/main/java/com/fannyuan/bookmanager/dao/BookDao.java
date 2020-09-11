package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookDao {
    List<Book> selectAll();
    Book selectById(int id);
    int addBook(Book book);
    void updateBookStatus(@Param("id") int id, @Param("status") int status);
    void updateBookRemains(@Param("id") int id, @Param("remains") int remains);
}
