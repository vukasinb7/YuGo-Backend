package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.PanicMapper;
import org.yugo.backend.YuGo.model.Panic;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class AllPanicsOut {
    private long totalCount;
    private List<PanicOut> results;

    public AllPanicsOut(Page<Panic> panics){
        this.results = panics.stream()
                .map(PanicMapper::fromPanictoDTO)
                .toList();
        this.totalCount = panics.getTotalElements();
    }
}
