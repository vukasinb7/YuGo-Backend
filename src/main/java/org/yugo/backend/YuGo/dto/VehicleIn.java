package org.yugo.backend.YuGo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;
@Getter @Setter
@NoArgsConstructor
public class VehicleIn {
    @NotNull(message = "Field (start) is required!")
    private VehicleType vehicleType;
    @NotBlank(message = "Field (model) is required!")
    @Size(max = 50, message = "Field (model) cannot be longer than 50 characters!")
    private String model;
    @NotBlank(message = "Field (licenseNumber) is required!")
    @Size(max = 15, message = "Field (licenseNumber) cannot be longer than 15 characters!")
    private String licenseNumber;
    @Valid
    @NotNull(message = "Field (currentLocation) is required!")
    private LocationInOut currentLocation;
    @NotNull(message = "Field (passengerSeats) is required!")
    private int passengerSeats;
    @NotNull(message = "Field (babyTransport) is required!")
    private Boolean babyTransport;
    @NotNull(message = "Field (petTransport) is required!")
    private Boolean petTransport;

    public VehicleIn(VehicleType vehicleType, String model, String licenseNumber, LocationInOut currentLocation,
                     int passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
