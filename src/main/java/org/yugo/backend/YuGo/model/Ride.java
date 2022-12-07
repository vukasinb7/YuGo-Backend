package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "Rides")
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
    private double totalCost;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "passenger_rides", joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<Passenger> passengers = new HashSet<Passenger>();

    @Getter @Setter
    @JoinColumn(name = "path_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Path> locations;

    @Getter @Setter
    @Column(name = "estimated_time")
    private int estimatedTimeInMinutes;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH,mappedBy = "ride")
    private Set<RideReview> rideReviews = new HashSet<RideReview>();


    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "status")
    private RideStatus status;

    @Getter @Setter
    @JoinColumn(name = "rejection_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private Rejection rejection;

    @Getter @Setter
    @Column(name = "is_panic_pressed")
    private Boolean isPanicPressed;

    @Getter @Setter
    @Column(name = "includes_babies")
    private Boolean babyTransport;

    @Getter @Setter
    @Column(name = "includes_pets")
    private Boolean petTransport;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_type_id")
    private VehicleTypePrice vehicleTypePrice;

    public Ride(LocalDateTime startTime, LocalDateTime endTime, double price, Driver driver, Set<Passenger> passengers, List<Path> paths, int estimatedTime, Set<RideReview> rideReviews, RideStatus status, Rejection rejection, Boolean isPanicPressed, Boolean includesBabies, Boolean includesPets, VehicleTypePrice vehicleTypePrice) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = price;
        this.driver = driver;
        this.passengers = passengers;
        this.locations = paths;
        this.estimatedTimeInMinutes = estimatedTime;
        this.rideReviews = rideReviews;
        this.status = status;
        this.rejection = rejection;
        this.isPanicPressed = isPanicPressed;
        this.babyTransport = includesBabies;
        this.petTransport = includesPets;
        this.vehicleTypePrice = vehicleTypePrice;
    }
}
