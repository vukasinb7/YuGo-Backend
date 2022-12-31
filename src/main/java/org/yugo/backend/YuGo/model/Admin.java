package org.yugo.backend.YuGo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.dto.UserDetailedIn;

@Entity
@NoArgsConstructor
@Getter @Setter
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(UserDetailedIn userDetailedIn){
        super(userDetailedIn);
    }

}
