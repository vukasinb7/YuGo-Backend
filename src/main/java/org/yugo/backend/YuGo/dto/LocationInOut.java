package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Location;
@Getter @Setter
@NoArgsConstructor
public class LocationInOut {
    @NotBlank(message = "Field (address) is required")
    @Size(max = 100,message = "address cannot be longer than 100 characters")
    private String address;
    @NotNull(message = "Field (latitude) is required")
    private double latitude;
    @NotNull(message = "Field (longitude) is required")
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
