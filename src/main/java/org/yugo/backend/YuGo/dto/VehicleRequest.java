package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleCategory;

public class VehicleRequest {

    @Getter @Setter
    private VehicleCategory vehicleCategory;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String licenseNumber;

    @Getter @Setter
    private LocationRespone currentLocation;

    @Getter @Setter
    private int passengerSeats;

    @Getter @Setter
    private Boolean babyTransport;

    @Getter @Setter
    private Boolean petTransport;

    public VehicleRequest(VehicleCategory vehicleCategory, String model, String licenseNumber, LocationRespone currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.vehicleCategory = vehicleCategory;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
