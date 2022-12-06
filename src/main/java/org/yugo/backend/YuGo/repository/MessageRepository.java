package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(value = "SELECT * FROM MESSAGES WHERE receiver_id = :userID", nativeQuery = true)
    List<Message> findMessagesByUser(@Param("userID") Integer userID);
}
