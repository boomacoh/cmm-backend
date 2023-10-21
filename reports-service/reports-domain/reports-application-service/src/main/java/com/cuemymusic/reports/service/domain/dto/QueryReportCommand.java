package com.cuemymusic.reports.service.domain.dto;

import java.time.ZonedDateTime;

public record QueryReportCommand(
        ZonedDateTime from,
        ZonedDateTime to
) {
}
