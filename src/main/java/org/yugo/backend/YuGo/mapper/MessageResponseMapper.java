package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.MessageResponse;
import org.yugo.backend.YuGo.model.Message;

@Component
public class MessageResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public MessageResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Message fromDTOtoMessage(MessageResponse dto) {
        return modelMapper.map(dto, Message.class);
    }

    public static MessageResponse fromMessagetoDTO(Message dto) {
        return modelMapper.map(dto, MessageResponse.class);
    }
}
