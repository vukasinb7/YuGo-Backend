package org.yugo.backend.YuGo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DriverStatus {
    @JsonProperty("online")
    private boolean online;
    public DriverStatus(boolean online){
        this.online = online;
    }
}
