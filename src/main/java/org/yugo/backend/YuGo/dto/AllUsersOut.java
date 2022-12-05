package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.User;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
public class AllUsersOut {
    @Getter @Setter
    private long totalCount;

    @Getter @Setter
    private List<UserDetailedInOut> results;

    public AllUsersOut(Page<User> users){
        this.results = users.stream()
                .map(UserDetailedMapper::fromUsertoDTO)
                .toList();

        this.totalCount = users.getTotalElements();
    }
}
