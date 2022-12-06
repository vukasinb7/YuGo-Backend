package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.PathMapper;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Panic;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class PanicOut {
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

    public PanicOut(Integer id, User user, Ride ride, LocalDateTime time, String reason) {
        this.id = id;
        this.user = UserDetailedMapper.fromUsertoDTO(user);
        this.ride = RideMapper.fromRidetoDTO(ride);
        this.time = time;
        this.locations = ride.getLocations().stream().map(PathMapper::fromPathtoDTO).toList();
        this.reason = reason;
    }

    public PanicOut(Panic panic) {
        this.id = panic.getId();
        this.user = UserDetailedMapper.fromUsertoDTO(panic.getUser());
        this.ride = RideMapper.fromRidetoDTO(panic.getRide());
        this.time = panic.getTime();
        this.locations = panic.getRide().getLocations().stream().map(PathMapper::fromPathtoDTO).toList();
        this.reason = panic.getReason();
    }
}
