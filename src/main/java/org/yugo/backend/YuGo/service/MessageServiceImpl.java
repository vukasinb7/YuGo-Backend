package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final WebSocketService webSocketService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserService userService,
                              WebSocketService webSocketService){
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.webSocketService = webSocketService;
    }

    @Override
    public Message insert(Message message){
        Message savedMessage = messageRepository.save(message);
        webSocketService.notifyUserAboutMessage(message.getReceiver().getId(), savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message get(Integer id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()){
            return messageOptional.get();
        }
        throw new NotFoundException("Message not found!");
    }

    @Override
    public List<Message> getUserMessages(Integer userId){
        userService.getUser(userId);
        return messageRepository.findMessagesByUser(userId);
    }

    @Override
    public List<Message> getUsersConversation(Integer user1Id, Integer user2Id){
        userService.getUser(user1Id);
        userService.getUser(user2Id);
        return messageRepository.findMessagesByUsers(user1Id, user2Id);
    }
}
