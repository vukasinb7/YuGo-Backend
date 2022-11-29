package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Admin")
public class Admin {
    @Getter @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter @Setter
    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
}
