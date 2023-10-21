package com.cuemymusic.reports.service.domain.dto;

import java.util.List;

public record ReportResponse(
    List<RecordDto> records
) {
}
