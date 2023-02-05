package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@NoArgsConstructor
public class ReviewIn {
    @NotNull(message = "Field (rating) cannot be null")
    @Range(min=1, max=5,message = "Field rating must have value between 1 and 5")
    private int rating;
    private String comment;

    public ReviewIn(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
