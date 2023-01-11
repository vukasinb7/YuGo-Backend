package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.MessageType;
@Getter @Setter
@NoArgsConstructor
public class MessageIn {
    @NotBlank(message = "Field (address) is required")
    @Size(max = 300,message = "address cannot be longer than 300 characters")
    private String message;
    @NotNull(message = "Field (type) is required")
    private MessageType type;
    @NotNull(message = "Field (rideId) is required")
    @Positive
    private Integer rideId;

    public MessageIn(String message, MessageType type, Integer rideId) {
        super();
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }
}
