package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.PanicOut;
import org.yugo.backend.YuGo.model.Panic;

@Component
public class PanicMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public PanicMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Panic fromDTOtoPanic(PanicOut dto) {
        return modelMapper.map(dto, Panic.class);
    }

    public static PanicOut fromPanicToDTO(Panic panic) {
        return modelMapper.map(panic, PanicOut.class);
    }
}
