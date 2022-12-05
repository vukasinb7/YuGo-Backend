package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.mapper.WorkingTimeMapper;
import org.yugo.backend.YuGo.model.WorkTime;

import java.util.List;
import java.util.stream.Stream;

public class AllWorkTimeOut {

    @Getter @Setter
    private int totalCount;

    @Getter @Setter
    private List<WorkTimeOut> workTimes;

    public AllWorkTimeOut(Stream<WorkTime> workTimes){
        this.workTimes = workTimes.map(WorkingTimeMapper::fromWorkTimeToDTO).toList();
        this.totalCount = this.workTimes.size();
    }
}
