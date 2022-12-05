package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.PathInOut;
import org.yugo.backend.YuGo.model.Path;

@Component
public class PathMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public PathMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Path fromDTOtoPath(PathInOut dto) {
        return modelMapper.map(dto, Path.class);
    }

    public static PathInOut fromPathtoDTO(Path path) {
        return modelMapper.map(path, PathInOut.class);
    }
}
