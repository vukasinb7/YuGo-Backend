package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Panic;

import java.util.List;

@NoArgsConstructor
public class AllPanicsOut {
    @Getter
    @Setter
    private int totalCount;

    @Getter @Setter
    private List<Panic> results;

    public AllPanicsOut(List<Panic> panics){
        this.results = panics;
        this.totalCount = results.size();
    }
}
