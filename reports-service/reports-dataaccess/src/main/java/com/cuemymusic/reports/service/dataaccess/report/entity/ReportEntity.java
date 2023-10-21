package com.cuemymusic.reports.service.dataaccess.report.entity;

import com.cuemymusic.user.service.domain.valueobject.ReportId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "reports")
public class ReportEntity {
    @Id
    private UUID id;
    private String clubName;
    private String rinkName;
    private String userName;
    private String songFriendlyName;
    private String songFileName;
    private ZonedDateTime timePlayed;
    private String copyRights;
    private UUID deviceId;
}
