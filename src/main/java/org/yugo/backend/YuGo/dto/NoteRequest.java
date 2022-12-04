package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class NoteRequest {
    @Getter @Setter
    String message;

    public NoteRequest(String message){
        this.message = message;
    }
}
