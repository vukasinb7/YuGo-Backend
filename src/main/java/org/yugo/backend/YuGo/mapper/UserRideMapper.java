package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.UserRideOut;
import org.yugo.backend.YuGo.model.Ride;

@Component
public class UserRideMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserRideMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Ride fromDTOtoRide(UserRideOut dto) {
        return modelMapper.map(dto, Ride.class);
    }

    public static UserRideOut fromRidetoDTO(Ride ride) {
        return modelMapper.map(ride, UserRideOut.class);
    }
}
