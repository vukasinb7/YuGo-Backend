package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;

@Getter @Setter
@NoArgsConstructor
public class VehicleTypeOutReduced {
    private Integer id;
    private VehicleType vehicleType;
    private String imgPath;

    public VehicleTypeOutReduced(Integer id, VehicleType vehicleType, String imgPath) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.imgPath = imgPath;
    }
}
