package org.yugo.backend.YuGo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.VehicleIn;


@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "licence_plate_number", nullable = false)
    private String licencePlateNumber;
    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location currentLocation;
    @Column(name = "are_babies_allowed", nullable = false)
    private Boolean areBabiesAllowed;
    @Column(name = "are_pets_allowed", nullable = false)
    private Boolean arePetsAllowed;

    public Vehicle(VehicleIn vehicleIn){
        this.vehicleType = vehicleIn.getVehicleType();
        this.model = vehicleIn.getModel();
        this.licencePlateNumber = vehicleIn.getLicenseNumber();
        Location location = new Location();
        location.setAddress(vehicleIn.getCurrentLocation().getAddress());
        location.setLatitude(vehicleIn.getCurrentLocation().getLatitude());
        location.setLongitude(vehicleIn.getCurrentLocation().getLongitude());
        this.currentLocation = location;
        this.numberOfSeats = vehicleIn.getPassengerSeats();
        this.areBabiesAllowed = vehicleIn.getBabyTransport();
        this.arePetsAllowed = vehicleIn.getBabyTransport();
    }
}
