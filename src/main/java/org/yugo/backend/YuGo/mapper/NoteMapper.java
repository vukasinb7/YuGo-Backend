package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.NoteOut;
import org.yugo.backend.YuGo.model.Note;

@Component
public class NoteMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public NoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Note fromDTOtoNote(NoteOut dto) {
        return modelMapper.map(dto, Note.class);
    }

    public static NoteOut fromNotetoDTO(Note dto) {
        return modelMapper.map(dto, NoteOut.class);
    }
}
