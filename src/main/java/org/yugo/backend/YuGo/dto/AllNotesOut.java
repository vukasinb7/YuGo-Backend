package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.NoteMapper;
import org.yugo.backend.YuGo.model.Note;

import java.util.List;
import java.util.stream.Stream;

public class AllNotesOut {
    @Getter @Setter
    private int totalCount;

    @Getter @Setter
    private List<NoteOut> results;

    public AllNotesOut(Stream<Note> noteStream){
        this.results = noteStream.map(NoteMapper::fromNotetoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
