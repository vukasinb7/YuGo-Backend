package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.WorkTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WorkTimeRepository extends JpaRepository<WorkTime,Integer> {

    @Query(value = "SELECT wt FROM WorkTime wt WHERE wt.driver.id = ?1 and wt.startTime >= ?2 and wt.endTime < ?3 ORDER BY wt.id")
    Page<WorkTime> findWorkTimesByDriverAndStartTimeAndEndTimePageable(Integer driverId, Pageable page, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT wt FROM WorkTime wt WHERE wt.driver.id = ?1 and wt.startTime >= ?2 and wt.endTime < ?3 ORDER BY wt.id")
    List<WorkTime> findWorkTimesByDriverAndStartTimeAndEndTime(Integer driverId, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT NVL(SUM(DATEDIFF(hour, end_time, start_time)), 0) FROM work_times WHERE end_time IS NOT NULL AND driver_id=:driverID AND start_time > DATEADD(hour, -24, CURRENT_TIMESTAMP);", nativeQuery = true)
    double getTotalWorkTimeInLast24Hours(@Param("driverID") Integer driverID);

    Optional<WorkTime> findWorkTimeByEndTimeIsNull();
}
// SELECT SUM(DATEDIFF(hour, start_time, end_time)) FROM work_times WHERE end_time IS NOT NULL AND start_time > DATEADD(hour, -24, CURRENT_TIMESTAMP);