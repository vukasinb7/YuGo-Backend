package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.VehicleOut;
import org.yugo.backend.YuGo.model.Vehicle;

@Component
public class VehicleMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public VehicleMapper(ModelMapper modelMapper) {
        VehicleMapper.modelMapper = modelMapper;
    }

    public static VehicleOut fromVehicleToDTO(Vehicle vehicle) {
        return new VehicleOut(vehicle);
    }
}
