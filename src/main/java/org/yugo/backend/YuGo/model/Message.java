package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
public class Message {
    @OneToOne
    @Getter @Setter
    @Column(name = "sender", nullable = false)
    private User sender;

    @OneToOne
    @Getter @Setter
    @Column(name = "receiver", nullable = false)
    private User receiver;

    @Getter @Setter
    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @Getter @Setter
    @Column(name = "sending_time", nullable = false)
    private LocalDateTime sendingTime;

    @Enumerated(EnumType.ORDINAL)
    @Getter @Setter
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @Getter @Setter
    @Column(name = "ride_ID")
    private Integer rideID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
