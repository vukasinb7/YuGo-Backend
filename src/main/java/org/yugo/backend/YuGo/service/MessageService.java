package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message insert(Message message);

    List<Message> getAll();

    Message get(Integer id);

    List<Message> getUserMessages(Integer userId);
}
