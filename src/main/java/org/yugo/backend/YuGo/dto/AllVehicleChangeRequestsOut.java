package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.VehicleChangeRequestMapper;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class AllVehicleChangeRequestsOut {
    private long totalCount;
    private List<VehicleChangeRequestOut> results;

    public AllVehicleChangeRequestsOut(Page<VehicleChangeRequest> vehicleChangeRequests){
        this.results = vehicleChangeRequests.stream().
                map(VehicleChangeRequestMapper::fromVehicleChangeRequestToDTO).toList();
        this.totalCount = vehicleChangeRequests.getTotalElements();
    }
}
