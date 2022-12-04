package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class NoteIn {
    @Getter @Setter
    String message;

    public NoteIn(String message){
        this.message = message;
    }
}
