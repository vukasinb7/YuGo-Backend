package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.ReviewResponse;
import org.yugo.backend.YuGo.dto.UserResponse;
import org.yugo.backend.YuGo.model.RideReview;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.VehicleReview;

@Component
public class ReviewsResponseMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public ReviewsResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static VehicleReview fromDTOtoVehicleReview(ReviewResponse dto) {
        return modelMapper.map(dto, VehicleReview.class);
    }

    public static ReviewResponse fromVehicleReviewtoDTO(VehicleReview dto) {
        return modelMapper.map(dto, ReviewResponse.class);
    }

    public static RideReview fromDTOtoRideReview(ReviewResponse dto) {
        return modelMapper.map(dto, RideReview.class);
    }

    public static ReviewResponse fromRideReviewtoDTO(RideReview dto) {
        return modelMapper.map(dto, ReviewResponse.class);
    }
}
