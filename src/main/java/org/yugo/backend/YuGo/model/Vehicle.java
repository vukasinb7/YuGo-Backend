package org.yugo.backend.YuGo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.VehicleRequest;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Vehicles")
public class Vehicle {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location currentLocation;

    @Getter @Setter
    @Column(name = "are_babies_allowed", nullable = false)
    private Boolean areBabiesAllowed;

    @Getter @Setter
    @Column(name = "are_pets_allowed", nullable = false)
    private Boolean arePetsAllowed;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VehicleReview> reviews = new HashSet<VehicleReview>();

    public Vehicle(){

    }

    public Vehicle(VehicleRequest vehicleRequest){
        this.vehicleCategory = vehicleRequest.getVehicleCategory();
        this.model = vehicleRequest.getModel();
        this.licencePlateNumber = vehicleRequest.getLicenseNumber();
        Location location = new Location();
        location.setAddress(vehicleRequest.getCurrentLocation().getAddress());
        location.setLatitude(vehicleRequest.getCurrentLocation().getLatitude());
        location.setLongitude(vehicleRequest.getCurrentLocation().getLongitude());
        this.currentLocation = location;
        this.numberOfSeats = vehicleRequest.getPassengerSeats();
        this.areBabiesAllowed = vehicleRequest.getBabyTransport();
        this.arePetsAllowed = vehicleRequest.getBabyTransport();
    }
}
