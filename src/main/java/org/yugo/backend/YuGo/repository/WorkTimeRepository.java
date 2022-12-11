package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.WorkTime;

import java.time.LocalDateTime;

public interface WorkTimeRepository extends JpaRepository<WorkTime,Integer> {

    @Query(value = "SELECT wt FROM WorkTime wt WHERE wt.driver.id = ?1 and wt.startTime >= ?2 and wt.endTime < ?3 ORDER BY wt.id")
    public Page<WorkTime> findWorkTimesByDriverAndStartTimeAndEndTimePageable(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);
}
