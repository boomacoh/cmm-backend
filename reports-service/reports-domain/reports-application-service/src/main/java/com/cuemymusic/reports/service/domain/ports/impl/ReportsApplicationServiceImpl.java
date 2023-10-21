package com.cuemymusic.reports.service.domain.ports.impl;

import com.cuemymusic.reports.service.domain.dto.QueryReportCommand;
import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.dto.ReportResponse;
import com.cuemymusic.reports.service.domain.ports.impl.handlers.AddRecordHandler;
import com.cuemymusic.reports.service.domain.ports.impl.handlers.FindReportHandler;
import com.cuemymusic.reports.service.domain.ports.input.ReportsApplicationService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReportsApplicationServiceImpl implements ReportsApplicationService {
    private final AddRecordHandler addRecordHandler;
    private final FindReportHandler findReportHandler;
    public ReportsApplicationServiceImpl(AddRecordHandler addRecordHandler, FindReportHandler findReportHandler) {
        this.addRecordHandler = addRecordHandler;
        this.findReportHandler = findReportHandler;
    }

    @Override
    public void addRecord(String token, UUID id, RecordDto addRecordCommand) {
        addRecordHandler.addRecord(token,id,addRecordCommand);
    }

    @Override
    public ReportResponse findReport(String token, UUID id, QueryReportCommand queryReportCommand) {
        return findReportHandler.find(token , id, queryReportCommand);
    }
}
