package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.util.List;

public class AllRidesOut {
    @Getter
    @Setter
    private long totalCount;

    @Getter @Setter
    private List<RideDetailedOut> results;

    public AllRidesOut(Page<Ride> rides){
        this.results = rides.stream()
                .map(RideMapper::fromRidetoDTO)
                .toList();
        this.totalCount = rides.getTotalElements();
    }
}
