package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "RideReviews")
public class RideReview {
    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne
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

    public RideReview(String comment, int rating, Ride ride, Passenger passenger) {
        this.comment = comment;
        this.rating = rating;
        this.ride = ride;
        this.passenger = passenger;
    }
}
