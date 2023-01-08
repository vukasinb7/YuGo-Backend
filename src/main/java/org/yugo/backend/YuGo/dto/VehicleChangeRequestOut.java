package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Vehicle;
import org.yugo.backend.YuGo.model.VehicleChangeRequest;

@NoArgsConstructor
@Getter @Setter
public class VehicleChangeRequestOut {
    private Integer id;
    private UserDetailedInOut driver;
    private VehicleOut oldVehicle;
    private VehicleOut newVehicle;
    private String dateCreated;

    public VehicleChangeRequestOut(VehicleChangeRequest vehicleChangeRequest){
        this.id = vehicleChangeRequest.getId();
        this.driver = new UserDetailedInOut(vehicleChangeRequest.getDriver());
        this.oldVehicle = new VehicleOut(vehicleChangeRequest.getDriver().getVehicle());
        Vehicle newVehicle = vehicleChangeRequest.getVehicle();
        this.newVehicle = new VehicleOut(newVehicle.getId(), newVehicle.getVehicleType().toString(),
                newVehicle.getModel(), newVehicle.getLicencePlateNumber(),
                new LocationInOut(newVehicle.getCurrentLocation()),
                newVehicle.getNumberOfSeats(), newVehicle.getAreBabiesAllowed(), newVehicle.getArePetsAllowed());
        this.dateCreated = vehicleChangeRequest.getDateCreated().toString();
    }
}
