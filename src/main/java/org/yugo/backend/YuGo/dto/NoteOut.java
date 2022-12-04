package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
public class NoteOut {
    @Getter @Setter
    Integer id;
    @Getter @Setter
    String message;

    @Getter @Setter
    LocalDateTime date;

    public NoteOut(Integer id, String message, LocalDateTime date){
        this.id = id;
        this.message = message;
        this.date = date;
    }
}
