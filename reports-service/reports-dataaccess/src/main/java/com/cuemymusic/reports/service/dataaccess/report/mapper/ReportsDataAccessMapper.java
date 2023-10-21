package com.cuemymusic.reports.service.dataaccess.report.mapper;

import com.cuemymusic.reports.service.dataaccess.report.entity.ReportEntity;
import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.reports.service.domain.entity.builder.ReportBuilder;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.ReportId;
import org.springframework.stereotype.Component;

@Component
public class ReportsDataAccessMapper {
    public Report reportEntityToReport(ReportEntity reportEntity){
        return new ReportBuilder()
                .setReportId(new ReportId(reportEntity.getId()))
                .setClubName(reportEntity.getClubName())
                .setCopyRights(reportEntity.getCopyRights())
                .setRinkName(reportEntity.getRinkName())
                .setSongFileName(reportEntity.getSongFileName())
                .setSongFriendlyName(reportEntity.getSongFriendlyName())
                .setTimePlayed(reportEntity.getTimePlayed())
                .setUserName(reportEntity.getUserName())
                .setDeviceId(new DeviceId(reportEntity.getDeviceId()))
                .createReport();
    }
    public ReportEntity reportToReportEntity(Report report){
        return ReportEntity.builder()
                .id(report.getReportId().getValue())
                .songFriendlyName(report.getSongFriendlyName())
                .timePlayed(report.getTimePlayed())
                .songFileName(report.getSongFileName())
                .rinkName(report.getRinkName())
                .userName(report.getUserName())
                .copyRights(report.getCopyRights())
                .clubName(report.getClubName())
                .deviceId(report.getDeviceId().getValue())
                .build();
    }
}
