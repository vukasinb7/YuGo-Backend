package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@NoArgsConstructor
public class NoteResponse {
    @Getter @Setter
    Integer id;
    @Getter @Setter
    String message;

    @Getter @Setter
    LocalDateTime date;

    public NoteResponse(Integer id, String message, LocalDateTime date){
        this.id = id;
        this.message = message;
        this.date = date;
    }
}
