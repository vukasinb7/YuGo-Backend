package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
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

    public AllVehicleReviewsOut(Page<VehicleReview> reviews){
        this.results = reviews.stream()
                .map(VehicleReviewMapper::fromVehicleReviewtoDTO)
                .toList();

        this.totalCount = reviews.getTotalElements();
    }
}
