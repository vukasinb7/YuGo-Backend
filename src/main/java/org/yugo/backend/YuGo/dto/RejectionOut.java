package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Rejection;

import java.time.LocalDateTime;

public class RejectionOut {
    @Getter @Setter
    String reason;
    @Getter @Setter
    LocalDateTime timeOfRejection;

    public RejectionOut(Rejection rejection){
        this.reason=rejection.getReason();
        this.timeOfRejection=rejection.getTimeOfRejection();
    }
}
