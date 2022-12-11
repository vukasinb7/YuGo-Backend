package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
public class NoteIn {
    String message;

    public NoteIn(String message){
        this.message = message;
    }
}
