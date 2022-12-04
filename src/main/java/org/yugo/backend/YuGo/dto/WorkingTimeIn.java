package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@NoArgsConstructor
public class WorkingTimeIn {

    @Getter @Setter
    private String start;

    @Getter @Setter
    private String end;

    public WorkingTimeIn(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public WorkingTimeIn(WorkTime workTime){
        this(workTime.getStartTime().toString(), workTime.getEndTime().toString());
    }
}
