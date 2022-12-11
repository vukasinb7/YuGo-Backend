package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Rejection;

import java.util.List;
import java.util.Optional;

public interface RejectionService {
    Rejection insert(Rejection rejection);

    List<Rejection> getAll();

    Optional<Rejection> get(Integer id);
}
