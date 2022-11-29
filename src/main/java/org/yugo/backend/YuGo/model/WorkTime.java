package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "WorkTime")
public class WorkTime {
    @Getter @Setter
    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Getter @Setter
    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @Column(name = "driver", nullable = false)
    private Driver driver;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
