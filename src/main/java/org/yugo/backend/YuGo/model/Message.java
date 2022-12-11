package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@NoArgsConstructor
@Getter @Setter
public class Message {
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @OneToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @Column(name = "message_content", nullable = false)
    private String messageContent;
    @Column(name = "sending_time", nullable = false)
    private LocalDateTime timeOfSending;
    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="ride_id")
    private Ride ride;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Message(User sender, User receiver, String messageContent, LocalDateTime timeOfSending, MessageType messageType, Ride ride) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageContent = messageContent;
        this.timeOfSending = timeOfSending;
        this.messageType = messageType;
        this.ride = ride;
    }
}
