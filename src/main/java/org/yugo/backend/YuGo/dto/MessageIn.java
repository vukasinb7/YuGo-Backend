package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.MessageType;

public class MessageIn {
    @Getter @Setter
    private Integer receiverId;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private MessageType type;
    @Getter @Setter
    private Integer rideId;

    public MessageIn(Integer receiverId, String message, MessageType type, Integer rideId) {
        super();
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }
}
