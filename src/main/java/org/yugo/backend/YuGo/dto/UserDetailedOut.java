package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;
@Getter @Setter
@NoArgsConstructor
public class UserDetailedOut {
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
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
