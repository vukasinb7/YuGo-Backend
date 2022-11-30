package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Paths")
public class Path{
    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "starting_point")
    private Location startingPoint;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "destination")
    private Location destination;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
