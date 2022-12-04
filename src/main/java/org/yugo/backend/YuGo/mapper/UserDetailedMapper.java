package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserDetailedInOut;
import org.yugo.backend.YuGo.model.User;
@Component
public class UserDetailedMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserDetailedMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static User fromDTOtoUser(UserDetailedInOut dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserDetailedInOut fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserDetailedInOut.class);
    }
}
