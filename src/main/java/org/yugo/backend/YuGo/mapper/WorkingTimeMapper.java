package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.WorkTimeIn;
import org.yugo.backend.YuGo.dto.WorkTimeOut;
import org.yugo.backend.YuGo.model.WorkTime;

@Component
public class WorkingTimeMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public WorkingTimeMapper(ModelMapper mapper){
        modelMapper = mapper;
    }
    public static WorkTimeOut fromWorkTimeToDTO(WorkTime workTime){
        return modelMapper.map(workTime, WorkTimeOut.class);
    }

    public static WorkTime fromDTOtoWorkTime(WorkTimeIn workTimeIn){
        return modelMapper.map(workTimeIn, WorkTime.class);
    }
}
