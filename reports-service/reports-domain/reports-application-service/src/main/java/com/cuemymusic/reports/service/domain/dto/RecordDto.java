package com.cuemymusic.reports.service.domain.dto;

import com.cuemymusic.user.service.domain.valueobject.ReportId;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record RecordDto(String clubName,
                        String rinkName,
                        String userName,
                        String songFriendlyName,
                        String songFileName,
                        ZonedDateTime timePlayed,
                        String copyRights)
{}
