package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.WorkingTimeMapper;
import org.yugo.backend.YuGo.model.WorkTime;

import java.util.List;
import java.util.stream.Stream;

@Getter @Setter
public class AllWorkTimeOut {
    private int totalCount;
    private List<WorkTimeOut> results;

    public AllWorkTimeOut(Stream<WorkTime> workTimes){
        this.results = workTimes.map(WorkingTimeMapper::fromWorkTimeToDTO).toList();
        this.totalCount = this.results.size();
    }
}
