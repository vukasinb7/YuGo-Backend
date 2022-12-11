package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "Paths")
public class Path{
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "starting_point")
    private Location departure;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "destination")
    private Location destination;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
