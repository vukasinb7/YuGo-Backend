package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

@NoArgsConstructor
@Getter @Setter
public class VehicleChangeRequestOut {
    private UserDetailedInOut driver;
    private VehicleOut oldVehicle;
    private VehicleOut newVehicle;

    public VehicleChangeRequestOut(VehicleChangeRequest vehicleChangeRequest){
        this.driver = new UserDetailedInOut(vehicleChangeRequest.getDriver());
        this.oldVehicle = new VehicleOut(vehicleChangeRequest.getDriver().getVehicle());
        this.newVehicle = new VehicleOut(vehicleChangeRequest.getVehicle());
    }
}
