package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

@Getter @Setter
@NoArgsConstructor
public class UserDetailedInOut {
    private Integer id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;

    public UserDetailedInOut(Integer id, String name, String surname, String profilePicture, String telephoneNumber, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }

    public UserDetailedInOut(User user) {
        this(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress());
    }
}
