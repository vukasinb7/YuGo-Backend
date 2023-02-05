package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@Getter @Setter
@NoArgsConstructor
public class EndWorkTimeIn {
    @NotBlank(message = "Field (end) is required")
    @Size(max = 50,message = "end cannot be longer than 50 characters")
    private String end;

    public EndWorkTimeIn(String end) {
        this.end = end;
    }

}
