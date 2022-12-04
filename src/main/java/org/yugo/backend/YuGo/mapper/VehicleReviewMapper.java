package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.ReviewOut;
import org.yugo.backend.YuGo.model.VehicleReview;

@Component
public class VehicleReviewMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public VehicleReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static VehicleReview fromDTOtoVehicleReview(ReviewOut dto) {
        return modelMapper.map(dto, VehicleReview.class);
    }

    public static ReviewOut fromVehicleReviewtoDTO(VehicleReview dto) {
        return modelMapper.map(dto, ReviewOut.class);
    }
}
