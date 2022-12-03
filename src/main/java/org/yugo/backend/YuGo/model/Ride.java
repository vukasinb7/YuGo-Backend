package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    private double price;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Getter @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "passenger_ride", joinColumns = @JoinColumn(name = "ride_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<Passenger> passengers = new HashSet<Passenger>();

    @Getter @Setter
    @JoinColumn(name = "path_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Path path;

    @Getter @Setter
    @Column(name = "estimated_time")
    private int estimatedTime;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH,mappedBy = "ride")
    private Set<RideReview> reviews = new HashSet<RideReview>();

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "status")
    private RideStatus status;

    @Getter @Setter
    @JoinColumn(name = "rejection_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "vehicle_type_id")
    private VehicleCategoryPrice vehicleCategoryPrice;

}
