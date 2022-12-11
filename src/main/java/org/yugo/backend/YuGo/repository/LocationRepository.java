package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
