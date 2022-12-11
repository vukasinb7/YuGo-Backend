package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.MessageType;
@Getter @Setter
public class MessageIn {
    private Integer receiverId;
    private String message;
    private MessageType type;
    private Integer rideId;

    public MessageIn(Integer receiverId, String message, MessageType type, Integer rideId) {
        super();
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }
}
