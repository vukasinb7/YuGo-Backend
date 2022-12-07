package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.RideReview;

@NoArgsConstructor
public class ReviewOut {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private int rating;

    @Getter @Setter
    private String comment;

    @Getter @Setter
    private UserSimplifiedOut passenger;

    public ReviewOut(Integer id, int rating, String review, Passenger passenger) {
        this.id = id;
        this.rating = rating;
        this.comment = review;
        this.passenger=new UserSimplifiedOut(passenger);
    }

    public ReviewOut(RideReview vehicleReview) {
        this(vehicleReview.getId(), vehicleReview.getRating(), vehicleReview.getComment(),vehicleReview.getPassenger());
    }
}
