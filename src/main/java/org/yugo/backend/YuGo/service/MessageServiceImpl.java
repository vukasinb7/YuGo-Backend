package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserService userService){
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message insert(Message message){
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

    @Override
    public List<Message> getUserMessages(Integer userId){
        userService.getUser(userId);
        return messageRepository.findMessagesByUser(userId);
    }
}
