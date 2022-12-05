package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class PanicDetailedOut {
    @Getter @Setter
    Integer id;
    @Getter @Setter
    UserDetailedInOut user;
    @Getter @Setter
    RideDetailedOut ride;
    @Getter @Setter
    LocalDateTime time;
    @Getter @Setter
    private List<PathInOut> locations;
    @Getter @Setter
    String reason;

    public PanicDetailedOut(Integer id, User user, Ride ride, LocalDateTime time, String reason) {
        this.id = id;
        this.user = UserDetailedMapper.fromUsertoDTO(user);
        this.ride = RideMapper.fromRidetoDTO(ride);
        this.time = time;
        this.locations = ride.getPaths().stream().map(PathMapper::fromPathtoDTO).toList();
        this.reason = reason;
    }
}
