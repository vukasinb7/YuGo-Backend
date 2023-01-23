package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.MessageOut;
import org.yugo.backend.YuGo.dto.VehicleChangeRequestOut;
import org.yugo.backend.YuGo.model.Message;

@Component
public class MessageMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public MessageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Message fromDTOtoMessage(MessageOut dto) {
        return modelMapper.map(dto, Message.class);
    }

    public static MessageOut fromMessagetoDTO(Message message) {
        return new MessageOut(message);
    }
}
