package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Document;
@Getter @Setter
@NoArgsConstructor
public class DocumentOut {
    private Integer id;
    private String name;
    private String documentImage;
    private Integer driverId;

    public DocumentOut(Integer id, String name, String documentImage, Integer driverId) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }

    public DocumentOut(Document document){
        this(document.getId(), document.getName(), document.getImage(), document.getDriver().getId());
    }
}
