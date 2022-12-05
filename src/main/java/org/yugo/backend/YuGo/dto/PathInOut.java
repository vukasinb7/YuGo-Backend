package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.LocationMapper;
import org.yugo.backend.YuGo.model.Path;

public class PathInOut {

    @Getter @Setter
    private LocationInOut departure;

    @Getter @Setter
    private LocationInOut destination;

    public PathInOut(LocationInOut departure, LocationInOut destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public PathInOut(Path path){
        this(LocationMapper.fromLocationtoDTO( path.getStartingPoint()),LocationMapper.fromLocationtoDTO(path.getDestination()));
    }


}
