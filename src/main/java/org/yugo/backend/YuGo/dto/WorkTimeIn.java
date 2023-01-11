package org.yugo.backend.YuGo.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@Getter @Setter
@NoArgsConstructor
public class WorkTimeIn {
    private String start;

    public WorkTimeIn(String start) {
        this.start = start;
    }

    public WorkTimeIn(WorkTime workTime){
        this(workTime.getStartTime().toString());
    }
}
