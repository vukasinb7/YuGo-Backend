package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

@NoArgsConstructor
public class UserSimplifiedOut {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String email;

    public UserSimplifiedOut(Integer id, String email) {
        super();
        this.id = id;
        this.email = email;
    }

    public UserSimplifiedOut(User user) {
        this(user.getId(), user.getName());
    }
}
