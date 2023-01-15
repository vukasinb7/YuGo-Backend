package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(value = "SELECT m FROM Message m WHERE m.receiver.id = :userId OR m.sender.id = :userId ORDER BY m.timeOfSending")
    List<Message> findMessagesByUser(@Param("userId") Integer userId);

    @Query(value = "SELECT m FROM Message m WHERE (m.receiver.id = :user1Id AND m.sender.id = :user2Id) OR " +
            "(m.receiver.id = :user2Id AND m.sender.id = :user1Id)ORDER BY m.timeOfSending")
    List<Message> findMessagesByUsers(@Param("user1Id") Integer user1Id, @Param("user2Id") Integer user2Id);


}
