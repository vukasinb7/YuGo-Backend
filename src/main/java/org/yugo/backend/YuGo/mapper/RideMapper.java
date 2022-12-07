package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.RideDetailedOut;
import org.yugo.backend.YuGo.model.Ride;

@Component
public class RideMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public RideMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Ride fromDTOtoRide(RideDetailedOut dto) {
        return modelMapper.map(dto, Ride.class);
    }

    public static RideDetailedOut fromRidetoDTO(Ride ride) {
        return modelMapper.map(ride, RideDetailedOut.class);
    }
}
