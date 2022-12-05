package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;
@NoArgsConstructor
public class UserDetailedInOut {

    @Getter @Setter
    private Integer id;

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
        this(user.getId(), user.getName(), user.getSurName(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress());
    }
}
