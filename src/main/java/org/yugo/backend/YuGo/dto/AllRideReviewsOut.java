package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideReviewMapper;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

@Getter @Setter
public class AllRideReviewsOut {
    private long totalCount;
    private List<ReviewOut> results;


    public AllRideReviewsOut(List<RideReview> reviews){
        this.results = reviews.stream()
                .map(RideReviewMapper::fromRideReviewtoDTO)
                .toList();

        this.totalCount = reviews.size();
    }
}
