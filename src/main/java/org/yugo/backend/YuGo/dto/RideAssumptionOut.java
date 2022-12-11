package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class RideAssumptionOut {
    private Integer estimatedTimeInMinutes;
    private Integer estimatedCost;

    public RideAssumptionOut(Integer estimatedTimeInMinutes, Integer estimatedCost) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }
}