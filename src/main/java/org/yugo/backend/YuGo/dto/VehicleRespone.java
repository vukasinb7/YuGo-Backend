package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Vehicle;

@NoArgsConstructor
public class VehicleRespone {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Integer driverId;

    @Getter @Setter
    private String vehicleType;

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

    public VehicleRespone(Integer id, Integer driverId, String vehicleType, String model, String licenseNumber, LocationRespone currentLocation, int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.id = id;
        this.driverId = driverId;
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public VehicleRespone(Vehicle vehicle){
        this(
                vehicle.getId(),
                vehicle.getDriver().getId(),
                vehicle.getVehicleCategory().toString(),
                vehicle.getModel(),
                vehicle.getLicencePlateNumber(),
                new LocationRespone(vehicle.getCurrentLocation()),
                vehicle.getNumberOfSeats(),
                vehicle.getAreBabiesAllowed(),
                vehicle.getArePetsAllowed()
        );
    }
}
