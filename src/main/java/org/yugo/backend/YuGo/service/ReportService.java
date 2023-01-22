package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.dto.ReportOut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public interface ReportService {
    ReportOut getNumberOfRidesByUser(Integer userId, LocalDateTime from, LocalDateTime to);

    ReportOut getTotalCostOfRidesByUser(Integer userId, LocalDateTime from, LocalDateTime to);

    ReportOut getDistanceByUser(Integer userId, LocalDateTime from, LocalDateTime to);
}
