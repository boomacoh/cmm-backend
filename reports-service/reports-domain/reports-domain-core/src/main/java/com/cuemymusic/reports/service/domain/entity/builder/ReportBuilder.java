package com.cuemymusic.reports.service.domain.entity.builder;

import com.cuemymusic.reports.service.domain.entity.Report;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.ReportId;

import java.time.ZonedDateTime;

public class ReportBuilder {
    private ReportId reportId;
    private String clubName;
    private String rinkName;
    private String userName;
    private String songFriendlyName;
    private String songFileName;
    private ZonedDateTime timePlayed;
    private String copyRights;
    private DeviceId deviceId;

    public ReportBuilder setReportId(ReportId reportId) {
        this.reportId = reportId;
        return this;
    }

    public ReportBuilder setClubName(String clubName) {
        this.clubName = clubName;
        return this;
    }

    public ReportBuilder setRinkName(String rinkName) {
        this.rinkName = rinkName;
        return this;
    }

    public ReportBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public ReportBuilder setSongFriendlyName(String songFriendlyName) {
        this.songFriendlyName = songFriendlyName;
        return this;
    }

    public ReportBuilder setSongFileName(String songFileName) {
        this.songFileName = songFileName;
        return this;
    }

    public ReportBuilder setTimePlayed(ZonedDateTime timePlayed) {
        this.timePlayed = timePlayed;
        return this;
    }

    public ReportBuilder setCopyRights(String copyRights) {
        this.copyRights = copyRights;
        return this;
    }

    public ReportBuilder setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Report createReport() {
        return new Report(reportId, clubName, rinkName, userName, songFriendlyName, songFileName, timePlayed, copyRights, deviceId);
    }
}