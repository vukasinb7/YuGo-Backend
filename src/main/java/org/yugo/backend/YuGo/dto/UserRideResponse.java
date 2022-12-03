package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

@NoArgsConstructor
public class UserRideResponse {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String email;

    public UserRideResponse(Integer id, String email) {
        super();
        this.id = id;
        this.email = email;
    }

    public UserRideResponse(User user) {
        this(user.getId(), user.getName());
    }
}
