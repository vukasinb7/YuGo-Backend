package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.mapper.MessageResponseMapper;
import org.yugo.backend.YuGo.model.Message;

import java.util.List;
import java.util.stream.Stream;

public class AllUserMessagesResponse {
    @Getter @Setter
    private long totalCount;

    @Getter @Setter
    private List<MessageResponse> results;

    public AllUserMessagesResponse(Stream<Message> messageStream){
        this.results = messageStream
                .map(MessageResponseMapper::fromMessagetoDTO)
                .toList();

        this.totalCount = results.size();
    }
}
