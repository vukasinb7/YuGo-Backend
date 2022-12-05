package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserDetailedIn;
import org.yugo.backend.YuGo.dto.UserDetailedInOut;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.User;
@Component
public class UserDetailedMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserDetailedMapper(ModelMapper modelMapper) {
        UserDetailedMapper.modelMapper = modelMapper;
    }

    public static User fromDTOtoDriver(UserDetailedInOut dto) {
        return modelMapper.map(dto, User.class);
    }
    public static Driver fromDTOtoDriver(UserDetailedIn dto){return modelMapper.map(dto, Driver.class);}

    public static UserDetailedInOut fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserDetailedInOut.class);
    }
}
