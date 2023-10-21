package com.cuemymusic.reports.service.application.rest;

import com.cuemymusic.reports.service.domain.dto.QueryReportCommand;
import com.cuemymusic.reports.service.domain.dto.RecordDto;
import com.cuemymusic.reports.service.domain.dto.ReportResponse;
import com.cuemymusic.reports.service.domain.exceptions.ReportsDomainException;
import com.cuemymusic.reports.service.domain.ports.input.ReportsApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/manager/reports")
@PreAuthorize("hasAnyRole('MANAGER')")
@AllArgsConstructor
public class ReportsController {
    private final ReportsApplicationService reportsApplicationService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<ReportResponse> getCurrent(@PathVariable UUID id,
                                                     @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                     QueryReportCommand queryReportCommand
    ){
        final String token;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ReportsDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        ReportResponse querySongResponse =  reportsApplicationService.findReport(token, id,queryReportCommand);
        return ResponseEntity.ok(querySongResponse);
    }
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity addRecord(
            @PathVariable UUID id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            RecordDto recordDto
    ){
        final String token;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ReportsDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        reportsApplicationService.addRecord(token, id, recordDto);
        return ResponseEntity.ok().build();
    }

}
