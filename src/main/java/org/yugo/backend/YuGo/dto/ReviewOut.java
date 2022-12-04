package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.VehicleReview;

@NoArgsConstructor
public class ReviewOut {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private int rating;

    @Getter @Setter
    private String review;

    public ReviewOut(Integer id, int rating, String review) {
        this.id = id;
        this.rating = rating;
        this.review = review;
    }
    public ReviewOut(VehicleReview vehicleReview){
        this(vehicleReview.getId(),vehicleReview.getRating(),vehicleReview.getComment());
    }
    public ReviewOut(RideReview rideReview){
        this(rideReview.getId(),rideReview.getRating(),rideReview.getComment());
    }
}
