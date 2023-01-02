package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "FavoritePaths")
public class FavoritePath {
    @Column(name = "favorite_name")
    private String favoriteName;

    @JoinColumn(name = "path_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Path> locations;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "passenger_favorite_paths", joinColumns = @JoinColumn(name = "path_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "favorite_path_id", referencedColumnName = "id"))
    private Set<Passenger> passengers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_type_id")
    private VehicleTypePrice vehicleTypePrice;

    @Column(name = "includes_babies")
    private Boolean babyTransport;

    @Column(name = "includes_pets")
    private Boolean petTransport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public FavoritePath(String favoriteName, List<Path> locations, Set<Passenger> passengers, VehicleTypePrice vehicleTypePrice, Boolean babyTransport, Boolean petTransport) {
        this.favoriteName = favoriteName;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleTypePrice = vehicleTypePrice;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
