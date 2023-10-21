package com.cuemymusic.reports.service.domain.ports.output.repository.reports;

import com.cuemymusic.reports.service.domain.entity.Report;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface ReportsRepository {
    Report addRecord(Report report);
    List<Report> findInRange(UUID deviceId, ZonedDateTime from, ZonedDateTime to);
}
