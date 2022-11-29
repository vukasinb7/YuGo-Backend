package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Path")
public class Path{
    @OneToOne
    @Getter @Setter
    @Column(name = "starting_point", nullable = false)
    private Location startingPoint;

    @OneToOne
    @Getter @Setter
    @Column(name = "destination", nullable = false)
    private Location destination;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
