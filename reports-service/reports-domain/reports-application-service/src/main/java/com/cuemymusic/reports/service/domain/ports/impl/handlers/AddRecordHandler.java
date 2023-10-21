package com.cuemymusic.reports.service.domain.ports.impl.handlers;

import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.reports.service.domain.mapper.ReportDataMapper;
import com.cuemymusic.reports.service.domain.ports.output.repository.reports.ReportsRepository;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.ReportId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AddRecordHandler {
    private final ReportsRepository reportsRepository;
    private final ReportDataMapper reportDataMapper;

    public AddRecordHandler(ReportsRepository reportsRepository, ReportDataMapper reportDataMapper) {
        this.reportsRepository = reportsRepository;
        this.reportDataMapper = reportDataMapper;
    }
    @Transactional
    public void addRecord(String token, UUID deviceId, RecordDto recordDto){
        final ReportId reportId = new ReportId(UUID.randomUUID());
        final Report newRecord = reportDataMapper.recordDtoToRecord(recordDto);
        newRecord.setDeviceId(new DeviceId(deviceId));
        newRecord.setReportId(reportId);
        reportsRepository.addRecord(newRecord);
    }
}
