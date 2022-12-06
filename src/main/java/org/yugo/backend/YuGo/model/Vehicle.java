package org.yugo.backend.YuGo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.VehicleIn;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Table(name = "Vehicles")
public class Vehicle {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Getter @Setter
    @Column(name = "vehicle_category")
    private VehicleCategory vehicleCategory;

    @Getter @Setter
    @Column(name = "model", nullable = false)
    private String model;

    @Getter @Setter
    @Column(name = "licence_plate_number", nullable = false)
    private String licencePlateNumber;

    @Getter @Setter
    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location currentLocation;

    @Getter @Setter
    @Column(name = "are_babies_allowed", nullable = false)
    private Boolean areBabiesAllowed;

    @Getter @Setter
    @Column(name = "are_pets_allowed", nullable = false)
    private Boolean arePetsAllowed;

    public Vehicle(VehicleIn vehicleIn){
        this.vehicleCategory = vehicleIn.getVehicleCategory();
        this.model = vehicleIn.getModel();
        this.licencePlateNumber = vehicleIn.getLicenseNumber();
        Location location = new Location();
        location.setAddress(vehicleIn.getCurrentLocation().getAddress());
        location.setLatitude(vehicleIn.getCurrentLocation().getLatitude());
        location.setLongitude(vehicleIn.getCurrentLocation().getLongitude());
        this.currentLocation = location;
        this.numberOfSeats = vehicleIn.getPassengerSeats();
        this.areBabiesAllowed = vehicleIn.getBabyTransport();
        this.arePetsAllowed = vehicleIn.getBabyTransport();
    }
}
