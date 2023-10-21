package com.cuemymusic.reports.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.ReportId;

import java.time.ZonedDateTime;

public class Report {
    private ReportId reportId;
    private String clubName;
    private String rinkName;
    private String userName;
    private String songFriendlyName;
    private String songFileName;
    private ZonedDateTime timePlayed;
    private String copyRights;
    private DeviceId deviceId;

    public Report(ReportId reportId,
                  String clubName,
                  String rinkName,
                  String userName,
                  String songFriendlyName,
                  String songFileName,
                  ZonedDateTime timePlayed,
                  String copyRights,
                  DeviceId deviceId) {
        this.reportId = reportId;
        this.clubName = clubName;
        this.rinkName = rinkName;
        this.userName = userName;
        this.songFriendlyName = songFriendlyName;
        this.songFileName = songFileName;
        this.timePlayed = timePlayed;
        this.copyRights = copyRights;
        this.deviceId = deviceId;
    }

    public ReportId getReportId() {
        return reportId;
    }

    public void setReportId(ReportId reportId) {
        this.reportId = reportId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getRinkName() {
        return rinkName;
    }

    public void setRinkName(String rinkName) {
        this.rinkName = rinkName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSongFriendlyName() {
        return songFriendlyName;
    }

    public void setSongFriendlyName(String songFriendlyName) {
        this.songFriendlyName = songFriendlyName;
    }

    public String getSongFileName() {
        return songFileName;
    }

    public void setSongFileName(String songFileName) {
        this.songFileName = songFileName;
    }

    public ZonedDateTime getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(ZonedDateTime timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getCopyRights() {
        return copyRights;
    }

    public void setCopyRights(String copyRights) {
        this.copyRights = copyRights;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }
}
