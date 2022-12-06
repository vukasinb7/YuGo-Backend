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
    ReviewOut vehicleReviews;

    @Getter @Setter
    ReviewOut driverReviews;

    public AcumulatedReviewsOut(RideReview vehicleReviews,RideReview driverReviews){
        this.vehicleReviews=RideReviewMapper.fromRideReviewtoDTO(vehicleReviews);
        this.driverReviews=RideReviewMapper.fromRideReviewtoDTO(driverReviews);
    }

}
