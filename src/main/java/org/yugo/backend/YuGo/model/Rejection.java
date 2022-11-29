package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Rejection")
public class Rejection {
    @OneToOne
    @Getter @Setter
    @Column(name = "ride", nullable = false)
    private Ride ride;

    @OneToOne
    @Getter @Setter
    @Column(name = "user", nullable = false)
    private User user;

    @Getter @Setter
    @Column(name = "rejection_reason", nullable = false)
    private String rejectionReason;

    @Getter @Setter
    @Column(name = "rejection_time", nullable = false)
    private LocalDateTime rejectionTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
