package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.RideMapper;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.Panic;
import org.yugo.backend.YuGo.model.Ride;
import org.yugo.backend.YuGo.model.User;

import java.time.LocalDateTime;

@NoArgsConstructor
public class PanicOut {
    @Getter @Setter
    Integer id;
    @Getter @Setter
    UserDetailedOut user;
    @Getter @Setter
    RideDetailedOut ride;
    @Getter @Setter
    LocalDateTime time;
    @Getter @Setter
    String reason;

    public PanicOut(Integer id, UserDetailedOut user, Ride ride, LocalDateTime time, String reason) {
        this.id = id;
        this.user = user;
        this.ride = RideMapper.fromRidetoDTO(ride);
        this.time = time;
        this.reason = reason;
    }

    public PanicOut(Panic panic) {
        this.id = panic.getId();
        this.user = new UserDetailedOut(panic.getUser());
        this.ride = RideMapper.fromRidetoDTO(panic.getRide());
        this.time = panic.getTime();
        this.reason = panic.getReason();
    }
}
