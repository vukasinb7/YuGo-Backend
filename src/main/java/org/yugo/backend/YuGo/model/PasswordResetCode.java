package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.random.RandomGenerator;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "PasswordResetCodes")
public class PasswordResetCode {
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
    @Column(name = "life_span", nullable = false)
    private Duration lifeSpan;
    @Column(name = "valid", nullable = false)
    private boolean valid;
    @Id
    @Column(name = "code", nullable = false)
    private Integer code;

    public PasswordResetCode(User user, Duration lifeSpan) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();;
        this.lifeSpan = lifeSpan;
        this.valid = true;
        this.code = Math.abs(user.hashCode() + dateCreated.hashCode() + RandomGenerator.getDefault().nextInt(100));
    }
}
