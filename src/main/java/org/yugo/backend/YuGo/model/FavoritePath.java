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

    @JoinColumn(name = "fav_path_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Path> locations;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "passenger_favorite_paths", joinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "favorite_path_id", referencedColumnName = "id"))
    private Set<Passenger> passengers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "owner")
    private Passenger owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
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
