package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Document;

public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
