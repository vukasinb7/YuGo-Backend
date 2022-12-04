package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Panic;

import java.time.LocalDateTime;

@NoArgsConstructor
public class PanicSimplifiedOut {

    @Getter @Setter
    Integer id;

    @Getter @Setter
    Integer userId;

    @Getter @Setter
    Integer rideId;

    @Getter @Setter
    LocalDateTime time;

    @Getter @Setter
    String reason;

    public PanicSimplifiedOut(Integer id, Integer userId, Integer rideId, LocalDateTime time, String reason) {
        this.id = id;
        this.userId = userId;
        this.rideId = rideId;
        this.time = time;
        this.reason = reason;
    }
    public PanicSimplifiedOut(Panic panic){
        this(panic.getId(),panic.getUser().getId(),panic.getRide().getId(),panic.getTime(),panic.getReason());
    }
}
