package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Document;
import org.yugo.backend.YuGo.model.DocumentType;

@Getter @Setter
@NoArgsConstructor
public class DocumentOut {
    private Integer id;
    private String name;
    private Integer driverId;
    private DocumentType documentType;

    public DocumentOut(Integer id, String name, Integer driverId,DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.driverId = driverId;
        this.documentType=documentType;
    }

    public DocumentOut(Document document){
        this(document.getId(), document.getName(), document.getDriver().getId(),document.getType());
    }
}
