package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.dto.PathInOut;
import org.yugo.backend.YuGo.dto.RideOut;
import org.yugo.backend.YuGo.model.Path;
import org.yugo.backend.YuGo.model.Ride;

public class PathMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public PathMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Path fromDTOtoPath(PathInOut dto) {
        return modelMapper.map(dto, Path.class);
    }

    public static PathInOut fromPathtoDTO(Path dto) {
        return modelMapper.map(dto, PathInOut.class);
    }
}
