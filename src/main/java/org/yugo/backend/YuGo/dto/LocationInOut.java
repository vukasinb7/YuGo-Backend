package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Location;

@NoArgsConstructor
public class LocationInOut {

    @Getter @Setter
    private String address;

    @Getter @Setter
    private double latitude;

    @Getter @Setter
    private double longitude;

    public LocationInOut(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationInOut(Location location){
        this(location.getAddress(), location.getLatitude(), location.getLongitude());
    }
}
