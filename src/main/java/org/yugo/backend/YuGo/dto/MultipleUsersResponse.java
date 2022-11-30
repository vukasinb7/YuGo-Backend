package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

public class MultipleUsersResponse {
    @Getter @Setter
    private int count;

    @Getter @Setter
    private List<User> users;

    public MultipleUsersResponse(List<User> users){
        this.count = users.size();
        this.users = users;
    }
}
