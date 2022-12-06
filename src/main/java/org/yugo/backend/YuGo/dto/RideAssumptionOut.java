package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleCategory;

import java.util.List;

public class RideAssumptionOut {
    @Getter
    @Setter
    private Integer estimatedTimeInMinutes;
    @Getter
    @Setter
    private Integer estimatedCost;

    public RideAssumptionOut(Integer estimatedTimeInMinutes, Integer estimatedCost) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }
}