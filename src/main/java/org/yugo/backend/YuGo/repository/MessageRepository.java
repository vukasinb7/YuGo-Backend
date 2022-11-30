package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
