package org.yugo.backend.YuGo.dto;

import jakarta.validation.Valid;
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
    @NotBlank(message = "Field (documentImage) is required")
    @Size(max = 100,message = "documentImage cannot be longer than 100 characters")
    private String documentImage;

    @NotNull(message = "Field (documentType) is required")
    private DocumentType documentType;

    public DocumentIn(String documentImage,DocumentType documentType) {
        this.documentImage = documentImage;
        this.documentType=documentType;
    }
}
