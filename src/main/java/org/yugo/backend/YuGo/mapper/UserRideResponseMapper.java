package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.dto.UserRideResponse;
import org.yugo.backend.YuGo.model.User;

@Component
public class UserRideResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserRideResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static User fromDTOtoUser(UserRideResponse dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserRideResponse fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserRideResponse.class);
    }
}
