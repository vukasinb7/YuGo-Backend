package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "Locations")
public class Location {
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


}
