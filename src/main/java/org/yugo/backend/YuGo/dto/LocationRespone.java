package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Location;

@NoArgsConstructor
public class LocationRespone {

    @Getter @Setter
    private String address;

    @Getter @Setter
    private double latitude;

    @Getter @Setter
    private double longitude;

    public LocationRespone(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationRespone(Location location){
        this(location.getAddress(), location.getLatitude(), location.getLongitude());
    }
}
