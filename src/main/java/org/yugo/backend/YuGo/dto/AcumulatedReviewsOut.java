package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideReviewMapper;
import org.yugo.backend.YuGo.model.RideReview;

import java.util.List;

public class AcumulatedReviewsOut {
    @Getter @Setter
    List<ReviewOut> vehicleReviews;

    @Getter @Setter
    List<ReviewOut> driverReviews;

    public AcumulatedReviewsOut(List<RideReview> vehicleReviews,List<RideReview> driverReviews){
        this.vehicleReviews=vehicleReviews.stream().map(RideReviewMapper::fromRideReviewtoDTO).toList();
        this.driverReviews=driverReviews.stream().map(RideReviewMapper::fromRideReviewtoDTO).toList();
    }

}
