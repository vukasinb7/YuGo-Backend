package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.MessageResponse;
import org.yugo.backend.YuGo.dto.NoteResponse;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.model.Note;

@Component
public class NoteResponseMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public NoteResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Note fromDTOtoNote(NoteResponse dto) {
        return modelMapper.map(dto, Note.class);
    }

    public static NoteResponse fromNotetoDTO(Note dto) {
        return modelMapper.map(dto, NoteResponse.class);
    }
}
