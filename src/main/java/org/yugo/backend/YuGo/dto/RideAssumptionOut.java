package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;

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