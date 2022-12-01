package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.UserResponseMapper;
import org.yugo.backend.YuGo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class AllUsersResponse {
    @Getter @Setter
    private int totalCount;

    @Getter @Setter
    private List<UserResponse> results;

    public AllUsersResponse(List<User> users){
        this.totalCount = users.size();

        this.results = users.stream()
                .map(UserResponseMapper::fromUsertoDTO)
                .toList();
    }
}
