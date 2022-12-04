package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.ReviewsResponseMapper;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;
import java.util.stream.Stream;

public class AllRideReviewsResponse {
    @Getter
    @Setter
    private long totalCount;

    @Getter @Setter
    private List<ReviewResponse> results;


    public AllRideReviewsResponse(Stream<RideReview> reviews){
        this.results = reviews
                .map(ReviewsResponseMapper::fromRideReviewtoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
