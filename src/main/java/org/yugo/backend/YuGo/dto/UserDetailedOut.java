package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

public class UserDetailedOut {
    @Getter
    @Setter
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


    public UserDetailedOut(String name, String surname, String profilePicture, String telephoneNumber, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }
    public UserDetailedOut(User user){
        this.name=user.getName();
        this.surname=user.getSurname();
        this.profilePicture=user.getProfilePicture();
        this.telephoneNumber=user.getTelephoneNumber();
        this.email=user.getEmail();
        this.address=user.getAddress();
    }
}
