package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.ports.input.service.ClubApplicationService;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/public/clubs", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
public class ClubListController {
    private final ClubApplicationService clubApplicationService;
    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ClubListResponse> getInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        ClubListResponse clubListResponse = clubApplicationService.findByUser(token);
        return ResponseEntity.ok(clubListResponse);
    }

}
