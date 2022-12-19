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
@RequestMapping(value = "/vehicleType")
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
            output.add(new VehicleTypeOutReduced(vehicleType.getId(), vehicleType.getVehicleType(), vehicleType.getImagePath()));
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
    @GetMapping(
            value = "/price",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Double> getPrice(@RequestParam(name = "vehicle_type_id") Integer vehicleTypeID,
                                    @RequestParam(name = "from_lat") double fromLat,
                                    @RequestParam(name = "from_lng") double fromLng,
                                    @RequestParam(name = "to_lat") double toLat,
                                    @RequestParam(name = "to_lng") double toLng){
        Location from = new Location();
        from.setLatitude(fromLat);
        from.setLongitude(fromLng);
        Location to = new Location();
        to.setLatitude(toLat);
        to.setLongitude(toLng);
        double price = vehicleService.calculatePrice(vehicleTypeID, from, to);//TODO catch exception from service
        if(price == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
