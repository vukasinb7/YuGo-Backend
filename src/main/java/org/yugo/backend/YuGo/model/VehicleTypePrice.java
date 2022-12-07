package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "VehicleTypePrices")
public class VehicleTypePrice {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "type", nullable = false)
    private VehicleType vehicleType;
    @Getter @Setter
    @Column(name = "price_per_km", nullable = false)
    private double pricePerKM;
}
