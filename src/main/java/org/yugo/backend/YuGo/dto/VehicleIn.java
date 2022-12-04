package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleCategory;

@NoArgsConstructor
public class VehicleIn {

    @Getter @Setter
    private VehicleCategory vehicleCategory;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String licenseNumber;

    @Getter @Setter
    private LocationInOut currentLocation;

    @Getter @Setter
    private int passengerSeats;

    @Getter @Setter
    private Boolean babyTransport;

    @Getter @Setter
    private Boolean petTransport;

    public VehicleIn(VehicleCategory vehicleCategory, String model, String licenseNumber, LocationInOut currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.vehicleCategory = vehicleCategory;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
