package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.LocationInOut;
import org.yugo.backend.YuGo.model.Location;

@Component
public class LocationMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Location fromDTOtoLocation(LocationInOut dto) {
        return modelMapper.map(dto, Location.class);
    }

    public static LocationInOut fromLocationtoDTO(Location dto) {
        return modelMapper.map(dto, LocationInOut.class);
    }
}
