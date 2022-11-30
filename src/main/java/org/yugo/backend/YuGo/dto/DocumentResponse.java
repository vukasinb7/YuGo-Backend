package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;
import org.yugo.backend.YuGo.model.Document;

public class DocumentResponse {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String documentImage;

    @Getter @Setter
    private Integer driverId;

    public DocumentResponse(Integer id, String name, String documentImage, Integer driverId) {
        this.id = id;
        this.name = name;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }

    public DocumentResponse(Document document){
        this(document.getId(), document.getName(), document.getImage(), document.getDriver().getId());
    }
}
