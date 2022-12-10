package org.yugo.backend.YuGo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "Rejections")
public class Rejection {
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ride")
    private Ride ride;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "by_user")
    private User byUser;
    @Column(name = "rejection_reason", nullable = false)
    private String reason;
    @Column(name = "rejection_time", nullable = false)
    private LocalDateTime timeOfRejection;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Rejection(Ride ride, User byUser, String reason, LocalDateTime timeOfRejection) {
        this.ride = ride;
        this.byUser = byUser;
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }
}
