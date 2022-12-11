package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.yugo.backend.YuGo.mapper.MessageMapper;
import org.yugo.backend.YuGo.model.Message;

import java.util.List;
import java.util.stream.Stream;
@Getter @Setter
public class AllUserMessagesOut {
    private long totalCount;
    private List<MessageOut> results;

    public AllUserMessagesOut(List<Message> messages){
        this.results = messages.stream()
                .map(MessageMapper::fromMessagetoDTO)
                .toList();

        this.totalCount = messages.size();
    }
}
