package org.yugo.backend.YuGo.dto;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.VehicleTypePrice;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class FavoritePathIn {

    @NotBlank(message = "Field (favoriteName) is required")
    @Size(max = 50,message = "Name cannot be longer than 50 characters")
    private String favoriteName;

    @NotNull(message = "Field (locations) is required")
    @Valid
    private List<PathInOut> locations;
    @NotNull(message = "Field (passengers) is required")
    @Valid
    private List<UserSimplifiedOut> passengers;
    @NotBlank(message = "Field (vehicleType) is required")
    @Size(max = 30,message = "vehicleType cannot be longer than 30 characters")
    private String vehicleType;

    @NotNull(message = "Field (babyTransport) is required")
    private Boolean babyTransport;

    @NotNull(message = "Field (petTransport) is required")
    private Boolean petTransport;
}
