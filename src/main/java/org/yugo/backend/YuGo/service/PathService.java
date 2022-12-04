package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Path;

import java.util.List;
import java.util.Optional;

public interface PathService {
    Path insert(Path path);

    List<Path> getAll();

    Optional<Path> get(Integer id);
}
