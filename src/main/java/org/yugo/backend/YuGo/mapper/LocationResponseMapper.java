package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.LocationRespone;
import org.yugo.backend.YuGo.dto.UserRideResponse;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.User;

@Component
public class LocationResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public LocationResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Location fromDTOtoLocation(UserRideResponse dto) {
        return modelMapper.map(dto, Location.class);
    }

    public static LocationRespone fromLocationtoDTO(Location dto) {
        return modelMapper.map(dto, LocationRespone.class);
    }
}
