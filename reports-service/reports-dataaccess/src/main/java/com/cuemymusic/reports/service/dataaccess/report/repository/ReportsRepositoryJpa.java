package com.cuemymusic.reports.service.dataaccess.report.repository;

import com.cuemymusic.reports.service.dataaccess.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportsRepositoryJpa extends JpaRepository<ReportEntity, UUID> {
    List<ReportEntity> findByDeviceIdAndTimePlayedBetween(UUID deviceId,ZonedDateTime from, ZonedDateTime to);
}
