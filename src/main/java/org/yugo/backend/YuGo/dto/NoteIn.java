package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
public class NoteIn {

    @NotBlank(message = "Field (message) is required")
    @Size(max = 300,message = "message cannot be longer than 300 characters")
    String message;

    public NoteIn(String message){
        this.message = message;
    }
}
