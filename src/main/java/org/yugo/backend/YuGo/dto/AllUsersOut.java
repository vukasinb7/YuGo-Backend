package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.UserDetailedMapper;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class AllUsersOut {
    private long totalCount;
    private List<UserDetailedInOut> results;

    public AllUsersOut(Page<User> users){
        this.results = users.stream()
                .map(UserDetailedMapper::fromUsertoDTO)
                .toList();

        this.totalCount = users.getTotalElements();
    }
}
