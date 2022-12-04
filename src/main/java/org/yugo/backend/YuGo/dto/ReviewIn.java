package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ReviewIn {

    @Getter @Setter
    
    private int rating;

    @Getter @Setter
    private String comment;

    public ReviewIn(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
