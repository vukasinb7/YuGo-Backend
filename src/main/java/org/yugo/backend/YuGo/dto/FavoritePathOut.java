package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.UserSimplifiedMapper;
import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Passenger;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FavoritePathOut {

    private String favoriteName;
    private List<PathInOut> locations;
    private List<UserSimplifiedOut> passengers;
    private String vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
    private Integer id;

    public FavoritePathOut(FavoritePath path){
        this.favoriteName=path.getFavoriteName();
        this.vehicleType=path.getVehicleTypePrice().getVehicleType().toString();
        this.babyTransport=path.getBabyTransport();
        this.petTransport=path.getPetTransport();
        this.id=path.getId();
        this.locations=path.getLocations().stream().map(PathMapper::fromPathtoDTO).toList();
        this.passengers = path.getPassengers().stream().map(UserSimplifiedMapper::fromUsertoDTO).toList();

    }
}
