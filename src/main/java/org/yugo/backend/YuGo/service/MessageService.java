package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message add(Message message);

    List<Message> getAll();

    Optional<Message> get(Integer id);
}
