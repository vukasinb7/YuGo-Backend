package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.WorkTime;

@Getter @Setter
@NoArgsConstructor
public class EndWorkTimeIn {

    private String end;

    public EndWorkTimeIn(String end) {
        this.end = end;
    }

}
