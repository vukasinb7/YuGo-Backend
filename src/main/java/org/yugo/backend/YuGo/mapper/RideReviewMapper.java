package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.ReviewOut;
import org.yugo.backend.YuGo.model.RideReview;

@Component
public class RideReviewMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public RideReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public static RideReview fromDTOtoRideReview(ReviewOut dto) {
        return modelMapper.map(dto, RideReview.class);
    }

    public static ReviewOut fromRideReviewtoDTO(RideReview dto) {
        return modelMapper.map(dto, ReviewOut.class);
    }
}
