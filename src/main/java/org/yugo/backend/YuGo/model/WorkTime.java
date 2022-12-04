package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "WorkTimes")
public class WorkTime {
    @Getter @Setter
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Getter @Setter
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
