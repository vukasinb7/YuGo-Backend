package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Table(name = "UserActivations")
public class UserActivation {
    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
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

    public UserActivation(User user, LocalDateTime dateCreated, Duration lifeSpan) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.lifeSpan = lifeSpan;
    }
}
