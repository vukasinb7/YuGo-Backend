package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class DocumentIn {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String documentImage;

    @Getter @Setter
    private Integer driverId;

    public DocumentIn(String name, String documentImage, Integer driverId) {
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }
}
