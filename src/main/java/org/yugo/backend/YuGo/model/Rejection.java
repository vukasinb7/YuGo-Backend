package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Rejections")
public class Rejection {
    @OneToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    @JoinColumn(name = "ride")
    private Ride ride;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    @JoinColumn(name = "by_user")
    private User byUser;

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
