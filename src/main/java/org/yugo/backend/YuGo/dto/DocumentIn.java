package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DocumentIn {
    private String name;
    private String documentImage;

    public DocumentIn(String name, String documentImage) {
        this.name = name;
        this.documentImage = documentImage;
    }
}
