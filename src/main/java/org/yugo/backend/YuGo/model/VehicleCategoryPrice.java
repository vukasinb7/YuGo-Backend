package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "VehicleCategoryPrices")
public class VehicleCategoryPrice {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Getter @Setter
    @Column(name = "category", nullable = false)
    private VehicleCategory name;

    @Getter @Setter
    @Column(name = "price_per_km", nullable = false)
    private double pricePerKM;
}
