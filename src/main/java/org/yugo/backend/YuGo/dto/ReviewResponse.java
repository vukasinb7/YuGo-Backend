package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;

@NoArgsConstructor
public class ReviewResponse {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private int rating;

    @Getter @Setter
    private String review;

    public ReviewResponse(Integer id, int rating, String review) {
        this.id = id;
        this.rating = rating;
        this.review = review;
    }
    public ReviewResponse(VehicleReview vehicleReview){
        this(vehicleReview.getId(),vehicleReview.getRating(),vehicleReview.getComment());
    }
    public ReviewResponse(RideReview rideReview){
        this(rideReview.getId(),rideReview.getRating(),rideReview.getComment());
    }
}
