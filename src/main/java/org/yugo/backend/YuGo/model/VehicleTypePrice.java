package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "VehicleTypePrices")
public class VehicleTypePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType vehicleType;
    @Column(name = "price_per_km", nullable = false)
    private double pricePerKM;
}
