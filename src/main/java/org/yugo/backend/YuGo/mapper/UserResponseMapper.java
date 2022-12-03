package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.User;
@Component
public class UserResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static User fromDTOtoUser(UserResponse dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserResponse fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserResponse.class);
    }
}
