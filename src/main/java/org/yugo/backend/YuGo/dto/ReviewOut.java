package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.RideReview;

@NoArgsConstructor
public class ReviewOut {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private int rating;

    @Getter @Setter
    private String comment;

    public ReviewOut(Integer id, int rating, String review) {
        this.id = id;
        this.rating = rating;
        this.comment = review;
    }

    public ReviewOut(RideReview vehicleReview) {
        this(vehicleReview.getId(), vehicleReview.getRating(), vehicleReview.getComment());
    }
}
