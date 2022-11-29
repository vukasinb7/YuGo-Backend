package org.yugo.backend.YuGo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;


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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleType vehicleType;

    @Getter @Setter
    @Column(name = "model", nullable = false)
    private String model;

    @Getter @Setter
    @Column(name = "register_plate_number", nullable = false)
    private String registerPlateNumber;

    @Getter @Setter
    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location currentLocation;

    @Getter @Setter
    @Column(name = "are_babies_allowed", nullable = false)
    private Boolean areBabiesAllowed;

    @Getter @Setter
    @Column(name = "are_pets_allowed", nullable = false)
    private Boolean arePetsAllowed;

    @Getter @Setter
    @OneToMany(mappedBy = "Vehiles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VehicleReview> reviews = new HashSet<VehicleReview>();

    public Vehicle(){

    }
}
