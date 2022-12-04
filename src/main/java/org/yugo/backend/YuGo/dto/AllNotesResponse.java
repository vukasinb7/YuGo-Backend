package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.NoteResponseMapper;
import org.yugo.backend.YuGo.mapper.ReviewsResponseMapper;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.model.Panic;

import java.util.List;
import java.util.stream.Stream;

public class AllNotesResponse {
    @Getter @Setter
    private int totalCount;

    @Getter @Setter
    private List<NoteResponse> results;

    public AllNotesResponse(Stream<Note> noteStream){
        this.results = noteStream.map(NoteResponseMapper::fromNotetoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
