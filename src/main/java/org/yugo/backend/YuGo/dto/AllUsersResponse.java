package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.UserResponseMapper;
import org.yugo.backend.YuGo.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class AllUsersResponse {
    @Getter @Setter
    private long totalCount;

    @Getter @Setter
    private List<UserResponse> results;

    public AllUsersResponse(Stream<User> users){
        this.results = users
                .map(UserResponseMapper::fromUsertoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
