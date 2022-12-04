package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Message;
import org.yugo.backend.YuGo.model.MessageType;

import java.time.LocalDateTime;

public class MessageRequest {
    @Getter @Setter
    private Integer receiverId;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private MessageType type;
    @Getter @Setter
    private Integer rideId;

    public MessageRequest(Integer receiverId, String message, MessageType type, Integer rideId) {
        super();
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }
}
