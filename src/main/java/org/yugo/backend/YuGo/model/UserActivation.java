package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Table(name = "UserActivations")
public class UserActivation {

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Column(name = "user", nullable = false)
    private User user;

    @Getter @Setter
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Getter @Setter
    @Column(name = "life_span", nullable = false)
    private Duration lifeSpan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
