package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="VehicleReviews")
public class VehicleReview {
    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "rating", nullable = false)
    private int rating;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    public VehicleReview(String comment, int rating, Vehicle vehicle, Passenger passenger) {
        this.comment = comment;
        this.rating = rating;
        this.vehicle = vehicle;
        this.passenger = passenger;
    }
}
