package org.yugo.backend.YuGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yugo.backend.YuGo.dto.VehicleTypeOutReduced;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.VehicleTypePrice;
import org.yugo.backend.YuGo.service.VehicleService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/vehicleType")
public class VehicleTypeController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleTypeController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<VehicleTypeOutReduced>> getVehicleTypes(){
        List<VehicleTypePrice> vehicleTypePriceList = vehicleService.getAllVehicleTypes();
        List<VehicleTypeOutReduced> output = new ArrayList<>();
        for(VehicleTypePrice vehicleType: vehicleTypePriceList){
            output.add(new VehicleTypeOutReduced(vehicleType.getId(), vehicleType.getVehicleType(), vehicleType.getImagePath(), vehicleType.getPricePerKM()));
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
