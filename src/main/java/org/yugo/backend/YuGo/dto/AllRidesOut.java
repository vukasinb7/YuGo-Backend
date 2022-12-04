package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.util.List;
import java.util.stream.Stream;

public class AllRidesOut {
    @Getter
    @Setter
    private int totalCount;

    @Getter @Setter
    private List<RideOut> results;

    public AllRidesOut(Stream<Ride> rideStream){
        this.results = rideStream
                .map(RideMapper::fromRidetoDTO)
                .toList();
        this.totalCount = results.size();
    }
}
