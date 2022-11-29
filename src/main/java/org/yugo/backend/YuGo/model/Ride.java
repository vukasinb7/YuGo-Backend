package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Table(name = "Ride")
public class Ride {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Getter @Setter
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Getter @Setter
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Getter @Setter
    @Column(name = "price", nullable = false)
    private double price;

    @Getter @Setter
    @JoinColumn(name = "driver_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Driver driver;

    @Getter @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "passenger_ride", joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<Passenger> reviews = new HashSet<Passenger>();

    @Getter @Setter
    @JoinColumn(name = "path_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Path path;

    @Getter @Setter
    @Column(name = "estimated_time")
    private Duration estimatedTime;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY)
    private Set<RideReview> reviews = new HashSet<RideReview>();

    @Getter @Setter
    @Column(name = "status")
    private RideStatus status;

    @Getter @Setter
    @JoinColumn(name = "rejection_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Rejection rejection;

    @Getter @Setter
    @Column(name = "is_panic_pressed")
    private Boolean isPanicPressed;

    @Getter @Setter
    @Column(name = "includes_babies")
    private Boolean includesBabies;

    @Getter @Setter
    @Column(name = "includes_pets")
    private Boolean includesPets;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    public Ride(){

    }
}
