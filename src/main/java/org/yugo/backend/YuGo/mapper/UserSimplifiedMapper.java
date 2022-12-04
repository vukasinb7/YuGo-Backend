package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserSimplifiedOut;
import org.yugo.backend.YuGo.model.User;

@Component
public class UserSimplifiedMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserSimplifiedMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static User fromDTOtoUser(UserSimplifiedOut dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserSimplifiedOut fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserSimplifiedOut.class);
    }
}
