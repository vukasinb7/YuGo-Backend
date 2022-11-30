package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Table(name = "Panics")
public class Panic {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id")
    @Getter @Setter
    private Ride ride;

    @Getter @Setter
    @Column(name = "time_pressed", nullable = false)
    private LocalDateTime timePressed;

    @Getter @Setter
    @Column(name = "reason", nullable = false)
    private String reason;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
