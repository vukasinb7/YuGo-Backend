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

    public DocumentIn(String name, String documentImage) {
        this.name = name;
        this.documentImage = documentImage;
    }
}
