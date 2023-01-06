package org.yugo.backend.YuGo.dto;

import jakarta.persistence.*;
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

    private String favoriteName;
    private List<PathInOut> locations;
    private List<UserSimplifiedOut> passengers;
    private String vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
}
