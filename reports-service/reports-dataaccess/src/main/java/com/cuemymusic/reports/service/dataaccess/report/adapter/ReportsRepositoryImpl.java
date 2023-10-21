package com.cuemymusic.reports.service.dataaccess.report.adapter;

import com.cuemymusic.reports.service.dataaccess.report.mapper.ReportsDataAccessMapper;
import com.cuemymusic.reports.service.dataaccess.report.repository.ReportsRepositoryJpa;
import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.reports.service.domain.ports.output.repository.reports.ReportsRepository;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ReportsRepositoryImpl implements ReportsRepository {
    private final ReportsRepositoryJpa reportsRepositoryJpa;
    private final ReportsDataAccessMapper reportsDataAccessMapper;

    public ReportsRepositoryImpl(ReportsRepositoryJpa reportsRepositoryJpa,
                                 ReportsDataAccessMapper reportsDataAccessMapper) {
        this.reportsRepositoryJpa = reportsRepositoryJpa;
        this.reportsDataAccessMapper = reportsDataAccessMapper;
    }

    @Override
    public Report addRecord(Report report) {
        return reportsDataAccessMapper.reportEntityToReport(reportsRepositoryJpa.save(reportsDataAccessMapper.reportToReportEntity(report)));
    }

    @Override
    public List<Report> findInRange(UUID deviceId, ZonedDateTime from, ZonedDateTime to) {
        return reportsRepositoryJpa.findByDeviceIdAndTimePlayedBetween(deviceId, from,to).stream().map(reportsDataAccessMapper::reportEntityToReport).toList();
    }
}
