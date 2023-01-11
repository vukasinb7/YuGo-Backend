package org.yugo.backend.YuGo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.LocationMapper;
import org.yugo.backend.YuGo.model.Location;
import org.yugo.backend.YuGo.model.Path;
@Getter @Setter
@NoArgsConstructor
public class PathInOut {
    @NotNull(message = "Field (departure) is required")
    @Valid
    private LocationInOut departure;
    @NotNull(message = "Field (destination) is required")
    @Valid
    private LocationInOut destination;

    public PathInOut(LocationInOut departure, LocationInOut destination) {
        this.departure = departure;
        this.destination = destination;
    }



}
