package com.cuemymusic.club.service.application.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/report", produces = "application/vnd.api.v1+json")
//@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class ReportController {
}
