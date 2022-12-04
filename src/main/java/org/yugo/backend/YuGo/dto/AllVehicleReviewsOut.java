package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.VehicleReviewMapper;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;
import java.util.stream.Stream;

public class AllVehicleReviewsOut {
    @Getter
    @Setter
    private long totalCount;

    @Getter @Setter
    private List<ReviewOut> results;

    public AllVehicleReviewsOut(Stream<VehicleReview> reviews){
        this.results = reviews
                .map(VehicleReviewMapper::fromVehicleReviewtoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
