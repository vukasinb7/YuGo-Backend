package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

public class WorkingTimeResponse {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String start;

    @Getter @Setter
    private String end;

    public WorkingTimeResponse(Integer id, String start, String end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public WorkingTimeResponse(WorkTime workTime){
        this(workTime.getId(), workTime.getStartTime().toString(), workTime.getEndTime().toString());
    }
}
