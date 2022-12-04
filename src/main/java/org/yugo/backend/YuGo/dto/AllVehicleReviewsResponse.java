package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.ReviewsResponseMapper;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;

import java.util.List;
import java.util.stream.Stream;

public class AllVehicleReviewsResponse {
    @Getter
    @Setter
    private long totalCount;

    @Getter @Setter
    private List<ReviewResponse> results;

    public AllVehicleReviewsResponse(Stream<VehicleReview> reviews){
        this.results = reviews
                .map(ReviewsResponseMapper::fromVehicleReviewtoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
