package com.cuemymusic.reports.service.domain.mapper;

import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.reports.service.domain.entity.builder.ReportBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReportDataMapper {
    public Report recordDtoToRecord(RecordDto recordDto){
        return new ReportBuilder()
                .setUserName(recordDto.userName())
                .setTimePlayed(recordDto.timePlayed())
                .setSongFriendlyName(recordDto.songFriendlyName())
                .setSongFileName(recordDto.songFileName())
                .setRinkName(recordDto.rinkName())
                .setCopyRights(recordDto.copyRights())
                .setClubName(recordDto.clubName())
                .createReport();
    }

    public RecordDto recordToRecordDto(Report report){
        return RecordDto.builder()
                .clubName(report.getClubName())
                .copyRights(report.getCopyRights())
                .rinkName(report.getRinkName())
                .clubName(report.getClubName())
                .songFriendlyName(report.getSongFriendlyName())
                .songFileName(report.getSongFileName())
                .userName(report.getUserName())
                .build();
    }
}
