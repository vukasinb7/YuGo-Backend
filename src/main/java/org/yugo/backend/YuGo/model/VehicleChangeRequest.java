package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "VehicleChangeRequests")
public class VehicleChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VehicleChangeRequestStatus status;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public VehicleChangeRequest(Driver driver, Vehicle vehicle){
        this.driver = driver;
        this.vehicle = vehicle;
        this.status = VehicleChangeRequestStatus.PENDING;
        this.dateCreated = LocalDateTime.now();
    }
}
