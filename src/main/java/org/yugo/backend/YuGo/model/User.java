package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserRequest;
import org.yugo.backend.YuGo.dto.UserResponse;


@Entity
@Table(name="Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class User {
    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter @Setter
    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @Getter @Setter
    @Column(name = "phone", nullable = false)
    private String phone;

    @Getter @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter @Setter
    @Column(name = "address", nullable = false)
    private String address;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter @Setter
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @Getter @Setter
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    public User(){

    }
    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.lastName = userRequest.getLastName();
        this.profilePicture = userRequest.getProfilePicture();
        this.phone = userRequest.getPhone();
        this.email = userRequest.getEmail();
        this.address = userRequest.getAddress();
        this.password = userRequest.getPassword();
        this.isBlocked = false;
        this.isActive = false;
    }
}
