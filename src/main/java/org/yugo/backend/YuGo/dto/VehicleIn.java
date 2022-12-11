package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;
@Getter @Setter
@NoArgsConstructor
public class VehicleIn {
    private VehicleType vehicleType;
    private String model;
    private String licenseNumber;
    private LocationInOut currentLocation;
    private int passengerSeats;
    private Boolean babyTransport;
    private Boolean petTransport;

    public VehicleIn(VehicleType vehicleType, String model, String licenseNumber, LocationInOut currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
