package com.cuemymusic.reports.service.domain.ports.input;

import com.cuemymusic.reports.service.domain.dto.QueryReportCommand;
import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.dto.ReportResponse;

import java.util.UUID;

public interface ReportsApplicationService {
    void addRecord(String token, UUID id, RecordDto addRecordCommand);
    ReportResponse findReport(String token, UUID id, QueryReportCommand queryReportCommand);
}
