package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
public class Message {
    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "sender")
    private User sender;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "receiver")
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="ride_id")
    private Ride ride;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
