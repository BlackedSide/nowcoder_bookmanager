package com.fannyuan.bookmanager.dao;

import com.fannyuan.bookmanager.model.Ticket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TicketDao {
    @Insert({
            "insert into ticket "
                    + "(user_id, ticket_string, ticket_expiredtime)"
                    + " values "
                    + "(#{userId}, #{ticket}, #{expiredAt})"
    })
    int addTicket(Ticket ticket);

    @Select({
            "select "
                    + "user_id, ticket_string, ticket_expiredtime"
                    + " from ticket "
                    + "where user_id = #{uid}"
    })
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "ticket", column = "ticket_string"),
            @Result(property = "expiredAt", column = "ticket_expiredtime")
    })
    Ticket selectByUserId(int uid);

    @Select({
            "select "
                    + "user_id, ticket_string, ticket_expiredtime"
                    + " from ticket "
                    + "where ticket_string = #{ticket}"
    })
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "ticket", column = "ticket_string"),
            @Result(property = "expiredAt", column = "ticket_expiredtime")
    })
    Ticket selectByTicket(String ticket);

    @Delete({
            "delete from ticket "
                    + "where ticket_id = #{tid}"
    })
    void deleteTicketById(int tid);

    @Delete({
            "delete from ticket "
                    + "where ticket_string = #{ticket}"
    })
    void deleteTicket(String ticket);
}
