package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.MessageResponse;
import org.yugo.backend.YuGo.dto.RideResponse;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.model.Ride;

@Component
public class RideResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public RideResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Ride fromDTOtoRide(RideResponse dto) {
        return modelMapper.map(dto, Ride.class);
    }

    public static RideResponse fromRidetoDTO(Ride dto) {
        return modelMapper.map(dto, RideResponse.class);
    }
}
