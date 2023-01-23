package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Panic;

import java.util.List;
import java.util.Optional;

public interface PanicService {
    Panic insert(Panic panic);

    Page<Panic> getAll(Pageable page);

    Panic get(Integer id);
    Panic getByRideId(Integer rideId);

}
