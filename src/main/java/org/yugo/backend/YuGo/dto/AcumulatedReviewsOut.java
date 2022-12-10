package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideReviewMapper;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

@NoArgsConstructor
public class AcumulatedReviewsOut {
    @Getter @Setter
    ReviewOut vehicleReview;

    @Getter @Setter
    ReviewOut driverReview;

    public AcumulatedReviewsOut(RideReview vehicleReviews,RideReview driverReviews){
        this.vehicleReview=RideReviewMapper.fromRideReviewtoDTO(vehicleReviews);
        this.driverReview=RideReviewMapper.fromRideReviewtoDTO(driverReviews);
    }

}
