package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.VehicleChangeRequestOut;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

@Component
public class VehicleChangeRequestMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public VehicleChangeRequestMapper(ModelMapper modelMapper) {
        VehicleChangeRequestMapper.modelMapper = modelMapper;
    }

    public static VehicleChangeRequestOut fromVehicleChangeRequestToDTO(VehicleChangeRequest vehicleChangeRequest) {
        return modelMapper.map(vehicleChangeRequest, VehicleChangeRequestOut.class);
    }
}

