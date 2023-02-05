package org.yugo.backend.YuGo.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@Getter @Setter
@NoArgsConstructor
public class WorkTimeIn {
    @NotBlank(message = "Field (start) is required!")
    @Size(max = 50, message = "Field (start) cannot be longer than 50 characters!")
    private String start;

    public WorkTimeIn(String start) {
        this.start = start;
    }

    public WorkTimeIn(WorkTime workTime){
        this(workTime.getStartTime().toString());
    }
}
