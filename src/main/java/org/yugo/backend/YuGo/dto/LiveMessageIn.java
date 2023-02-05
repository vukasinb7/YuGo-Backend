package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class LiveMessageIn {
    Integer userId;
    String message;

    public LiveMessageIn(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
