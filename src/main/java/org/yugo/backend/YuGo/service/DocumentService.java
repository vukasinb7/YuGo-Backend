package org.yugo.backend.YuGo.service;

import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.model.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document insert(Document document) throws IOException;
    Document upload(Document document, MultipartFile file) throws IOException;

    List<Document> getAll();

    Document get(Integer id);

    void delete(Integer id);

    void deleteAllForDriver(Integer driverId);
}
