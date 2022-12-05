package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.RideReviewMapper;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;
import java.util.stream.Stream;

public class AllRideReviewsOut {
    @Getter
    @Setter
    private long totalCount;

    @Getter @Setter
    private List<ReviewOut> results;


    public AllRideReviewsOut(Page<RideReview> reviews){
        this.results = reviews.stream()
                .map(RideReviewMapper::fromRideReviewtoDTO)
                .toList();

        this.totalCount = reviews.getTotalElements();
    }
}
