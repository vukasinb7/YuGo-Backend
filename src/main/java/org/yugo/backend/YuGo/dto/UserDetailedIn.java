package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UserDetailedIn {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String profilePicture;

    @Getter @Setter
    private String telephoneNumber;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String password;

    public UserDetailedIn(String name, String surname, String profilePicture, String telephoneNumber, String email, String address, String password) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
