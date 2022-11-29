package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Review")
public class Review {
    @Getter @Setter
    @Column(name = "comment")
    private String comment;

    @Getter @Setter
    @Column(name = "rating", nullable = false)
    private int rating;

    @OneToOne
    @Getter @Setter
    @Column(name = "ride", nullable = false)
    private Ride ride;

    @OneToOne
    @Getter @Setter
    @Column(name = "passenger", nullable = false)
    private Passenger passenger;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
