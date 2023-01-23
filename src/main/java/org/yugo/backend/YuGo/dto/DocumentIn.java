package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.model.DocumentType;

@Getter @Setter
@NoArgsConstructor
public class DocumentIn {


    @NotBlank(message = "Field (name) is required")
    @Size(max = 50,message = "Name cannot be longer than 50 characters")
    private String name;

    @NotBlank(message = "Field (documentImage) is required")
    @Size(max = 100,message = "documentImage cannot be longer than 100 characters")
    private String documentImage;

    private DocumentType documentType;

    public DocumentIn(String name, String documentImage,DocumentType documentType) {
        this.name = name;
        this.documentImage = documentImage;
        this.documentType=documentType;
    }
}
