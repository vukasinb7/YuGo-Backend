package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Panic;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

@NoArgsConstructor
public class AllPanicsResponse {
    @Getter
    @Setter
    private int totalCount;

    @Getter @Setter
    private List<Panic> results;

    public AllPanicsResponse(List<Panic> panics){
        this.results = panics;
        this.totalCount = results.size();
    }
}
