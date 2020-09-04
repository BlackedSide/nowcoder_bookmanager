package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookDao {
    @Select({
            "select "
                    + "book_id, book_name, book_author, book_price, book_status, book_remains, book_stars, book_createtime, book_updatetime"
                    + " from book"
    })
    @Results({
            @Result(property = "id", column = "book_id"),
            @Result(property = "name", column = "book_name"),
            @Result(property = "author", column = "book_author"),
            @Result(property = "price", column = "book_price"),
            @Result(property = "status", column = "book_status"),
            @Result(property = "remains", column = "book_remains"),
            @Result(property = "stars", column = "book_stars"),
            @Result(property = "createTime", column = "book_createtime"),
            @Result(property = "updateTime", column = "book_updatetime")
    })
    List<Book> selectAll();

    @Select({
            "select "
                    + "book_id, book_name, book_author, book_price, book_status, book_remains, book_stars, book_createtime, book_updatetime"
                    + " from book"
                    + " where book_id = #{id}"
    })
    @Results({
            @Result(property = "id", column = "book_id"),
            @Result(property = "name", column = "book_name"),
            @Result(property = "author", column = "book_author"),
            @Result(property = "price", column = "book_price"),
            @Result(property = "status", column = "book_status"),
            @Result(property = "remains", column = "book_remains"),
            @Result(property = "stars", column = "book_stars"),
            @Result(property = "createTime", column = "book_createtime"),
            @Result(property = "updateTime", column = "book_updatetime")
    })
    Book selectById(int id);

    @Insert({
            "insert into book "
                    + "(book_name, book_author, book_price, book_remains, book_createtime, book_updatetime)"
                    + " values "
                    + "(#{name}, #{author}, #{price}, #{remains}, #{createTime}, #{updateTime})"
    })
    int addBook(Book book);

    @Update({
            "update book set "
                    + "book_status = #{status} where book_id = #{id}"
    })
    void updateBookStatus(@Param("id") int id, @Param("status") int status);

    @Update({
            "update book set "
                    + "book_remains = #{remains} where book_id = #{id}"
    })
    void updateBookRemains(int id, int remains);
}
