package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Panic;

import java.util.List;
import java.util.Optional;

public interface PanicService {
    Panic save(Panic panic);

    List<Panic> getAll();

    Optional<Panic> get(Integer id);
}
