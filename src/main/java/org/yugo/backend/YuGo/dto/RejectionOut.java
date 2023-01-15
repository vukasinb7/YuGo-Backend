package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Rejection;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class RejectionOut {
    String reason;
    String timeOfRejection;

    public RejectionOut(Rejection rejection){
        this.reason=rejection.getReason();
        this.timeOfRejection=rejection.getTimeOfRejection().toString();
    }
}
