package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Panic;

public interface PanicRepository extends JpaRepository<Panic,Integer> {
}
