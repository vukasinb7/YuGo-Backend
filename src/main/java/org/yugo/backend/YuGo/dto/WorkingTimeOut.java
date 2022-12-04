package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@NoArgsConstructor
public class WorkingTimeOut {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String start;

    @Getter @Setter
    private String end;

    public WorkingTimeOut(Integer id, String start, String end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public WorkingTimeOut(WorkTime workTime){
        this(workTime.getId(), workTime.getStartTime().toString(), workTime.getEndTime().toString());
    }
}
