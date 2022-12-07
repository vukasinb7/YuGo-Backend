package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Message;

import java.time.LocalDateTime;

@NoArgsConstructor
public class MessageOut {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private LocalDateTime timeOfSending;
    @Getter @Setter
    private Integer senderId;
    @Getter @Setter
    private Integer receiverId;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private Integer rideId;

    public MessageOut(Integer id, LocalDateTime timeOfSending, Integer senderId, Integer receiverId, String message, String type, Integer rideId) {
        this.id = id;
        this.timeOfSending = timeOfSending;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }

    public MessageOut(Message message) {
        this(message.getId(), message.getTimeOfSending(), message.getSender().getId(), message.getReceiver().getId(),
                message.getMessageContent(), message.getMessageType().toString(), message.getRide().getId());
    }
}
