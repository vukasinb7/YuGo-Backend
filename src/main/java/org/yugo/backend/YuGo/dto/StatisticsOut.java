package org.yugo.backend.YuGo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsOut {
    private Double totalIncome;
    private Double totalRides;

    private Double averageRating;
    private Double totalWorkHours;
    private Double totalDistance;
    private Double totalPassengers;
}
