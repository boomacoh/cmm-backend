package com.cuemymusic.reports.service.domain.ports.impl.handlers;

import com.cuemymusic.reports.service.domain.dto.QueryReportCommand;
import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.dto.ReportResponse;
import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.reports.service.domain.mapper.ReportDataMapper;
import com.cuemymusic.reports.service.domain.ports.output.repository.reports.ReportsRepository;
import com.cuemymusic.user.service.domain.valueobject.ReportId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class FindReportHandler {
    private final ReportsRepository reportsRepository;
    private final ReportDataMapper reportDataMapper;

    public FindReportHandler(ReportsRepository reportsRepository, ReportDataMapper reportDataMapper) {
        this.reportsRepository = reportsRepository;
        this.reportDataMapper = reportDataMapper;
    }
    @Transactional
    public ReportResponse find(String token ,UUID deviceId, QueryReportCommand queryReportCommand){
        List<Report> result =  reportsRepository.findInRange(deviceId, queryReportCommand.from(),queryReportCommand.to());
        return new ReportResponse(result.stream().map(reportDataMapper::recordToRecordDto).toList());
    }
}
