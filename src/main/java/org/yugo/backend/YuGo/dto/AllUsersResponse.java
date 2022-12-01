package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

public class AllUsersResponse {
    @Getter @Setter
    private int totalCount;

    @Getter @Setter
    private List<User> results;

    public AllUsersResponse(List<User> users){
        this.totalCount = users.size();
        this.results = users;
    }
}
