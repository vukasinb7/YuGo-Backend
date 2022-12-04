package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideResponseMapper;
import org.yugo.backend.YuGo.model.Ride;

import java.util.List;
import java.util.stream.Stream;

public class AllRidesResponse {
    @Getter
    @Setter
    private int totalCount;

    @Getter @Setter
    private List<RideResponse> results;

    public AllRidesResponse(Stream<Ride> rideStream){
        this.results = rideStream
                .map(RideResponseMapper::fromRidetoDTO)
                .toList();
        this.totalCount = results.size();
    }
}
