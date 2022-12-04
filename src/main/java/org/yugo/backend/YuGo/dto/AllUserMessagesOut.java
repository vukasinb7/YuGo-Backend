package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.MessageMapper;
import org.yugo.backend.YuGo.model.Message;

import java.util.List;
import java.util.stream.Stream;

public class AllUserMessagesOut {
    @Getter @Setter
    private long totalCount;

    @Getter @Setter
    private List<MessageOut> results;

    public AllUserMessagesOut(Stream<Message> messageStream){
        this.results = messageStream
                .map(MessageMapper::fromMessagetoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
