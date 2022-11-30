package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document add(Document document);

    List<Document> getAll();

    Optional<Document> get(Integer id);
}
