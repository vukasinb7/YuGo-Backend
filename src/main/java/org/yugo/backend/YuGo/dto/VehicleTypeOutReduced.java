package org.yugo.backend.YuGo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypeOutReduced {
    private Integer id;
    private VehicleType vehicleType;
    private String imgPath;
    private double pricePerKm;
}
