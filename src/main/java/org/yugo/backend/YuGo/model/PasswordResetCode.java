package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.time.Duration;
import java.time.LocalDateTime;

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
    @Id
    @Column(name = "code", nullable = false)
    private String code;

    public PasswordResetCode(User user, Duration lifeSpan) {
        this.code = String.valueOf(user.getEmail().hashCode());
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.lifeSpan = lifeSpan;
    }
}
