package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.Rejection;

public interface RejectionRepository extends JpaRepository<Rejection,Integer> {
}
