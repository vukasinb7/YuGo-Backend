package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public Message add(Message message){
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> get(Integer id) {
        return messageRepository.findById(id);
    }
}
