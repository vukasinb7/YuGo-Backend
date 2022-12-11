package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "Notes")
public class Note {
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Note(User user, String message, LocalDateTime date){
        this.user = user;
        this.message = message;
        this.date = date;
    }
}
