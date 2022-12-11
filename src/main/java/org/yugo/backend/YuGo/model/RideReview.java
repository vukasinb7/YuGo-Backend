package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "RideReviews")
public class RideReview {
    @Column(name = "comment")
    private String comment;
    @Column(name = "rating", nullable = false)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "ride")
    private Ride ride;
    @OneToOne
    @JoinColumn(name = "passenger")
    private Passenger passenger;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReviewType type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public RideReview(String comment, int rating, Ride ride, Passenger passenger, ReviewType type) {
        this.comment = comment;
        this.rating = rating;
        this.ride = ride;
        this.passenger = passenger;
        this.type = type;
    }
}
