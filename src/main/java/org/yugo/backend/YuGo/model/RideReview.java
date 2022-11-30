package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RideReviews")
public class RideReview {
    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "rating", nullable = false)
    private int rating;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "ride")
    private Ride ride;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "passenger")
    private Passenger passenger;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
