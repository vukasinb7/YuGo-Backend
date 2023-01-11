package org.yugo.backend.YuGo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.VehicleType;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class RideAssumptionIn {
    @NotNull(message = "Field (locations) is required")
    @Valid
    private List<PathInOut> locations;
    @NotNull(message = "Field (vehicleType) is required")
    private VehicleType vehicleType;
    @NotNull(message = "Field (babyTransport) is required")
    private boolean babyTransport;
    @NotNull(message = "Field (petTransport) is required")
    private boolean petTransport;

    public RideAssumptionIn(List<PathInOut> locations, VehicleType vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }
}
