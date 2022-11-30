package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.WorkTime;

public interface WorkTimeRepository extends JpaRepository<WorkTime,Integer> {
}
