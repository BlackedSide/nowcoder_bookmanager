package com.fannyuan.bookmanager.controllers;

import com.fannyuan.bookmanager.model.Book;
import com.fannyuan.bookmanager.model.User;
import com.fannyuan.bookmanager.model.UserOrder;
import com.fannyuan.bookmanager.service.BookService;
import com.fannyuan.bookmanager.service.UserOrderService;
import com.fannyuan.bookmanager.service.UserService;
import com.fannyuan.bookmanager.utils.ConcurrentUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private BookService bookService;

    @Resource
    private UserOrderService userOrderService;

    @GetMapping(path = "/{bookId:[0-9]+}/borrow")
    public ResponseEntity<UserOrder> borrowOneBook(@PathVariable("bookId") int book_id) {
        User user = ConcurrentUtils.getHost();

        Book book = bookService.getBookById(book_id);

        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (book.getStatus() == 1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (book.getRemains() > 0) {
            boolean success = bookService.updateBookRemains(book_id, book.getRemains() - 1);
            if (success) {
                UserOrder order = new UserOrder();
                order.setBookId(book_id);
                order.setUserId(user.getId());
                DateTime dateTime = new DateTime();
                order.setCreateTime(dateTime.toDate());
                order.setExpiredTime(dateTime.plusMonths(1).toDate());
                userOrderService.borrowOneBook(order);
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/select")
    public ResponseEntity<List<UserOrder>> selectOrders() {
        User user = ConcurrentUtils.getHost();

        List<UserOrder> userOrders = userOrderService.selectOrders(user.getId());

        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId:[0-9]+}/return")
    public ResponseEntity<UserOrder> returnOneBook(@PathVariable("orderId") int order_id) {
        UserOrder order = userOrderService.getOrderById(order_id);

        if (order != null) {
            bookService.addBookRemains(order.getBookId());
            userOrderService.returnOneBook(order_id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
