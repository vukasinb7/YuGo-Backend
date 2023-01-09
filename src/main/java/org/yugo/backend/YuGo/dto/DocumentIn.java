package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.model.DocumentType;

@Getter @Setter
@NoArgsConstructor
public class DocumentIn {
    private String name;
    String  documentImage;

    private DocumentType documentType;

    public DocumentIn(String name, String documentImage,DocumentType documentType) {
        this.name = name;
        this.documentImage = documentImage;
        this.documentType=documentType;
    }
}
