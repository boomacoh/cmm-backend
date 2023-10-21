package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.management.*;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.UserDeviceListResponse;
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
@RequestMapping(value = "/api/manage/clubs", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('MANAGER')")
@AllArgsConstructor
public class ManageClubController {
    private final ClubApplicationService clubApplicationService;
    @GetMapping("/")
    public ResponseEntity<ClubListResponse> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        ClubListResponse clubListResponse = clubApplicationService.findManagedByUser(token);
        return ResponseEntity.ok(clubListResponse);
    }
    @GetMapping("/{clubId}/disabled")
    public ResponseEntity<UsersListResponse> findAll(@PathVariable UUID clubId){
        UsersListResponse res = clubApplicationService.findDisabledUsers(clubId);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/{clubId}/{userId}")
    public ResponseEntity<CreateClubResponse> enableUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                         @PathVariable UUID clubId,
                                                         @PathVariable UUID userId
                                                         ){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        clubApplicationService.disableUser(token,clubId,userId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{clubId}/{userId}")
    public ResponseEntity<CreateClubResponse> disableUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID clubId,
            @PathVariable UUID userId
    ){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        clubApplicationService.enableUser(token,clubId,userId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{clubId}")
    public ResponseEntity<CreateClubResponse> enableAllUsers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID clubId
    ){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        clubApplicationService.enableAll(token,clubId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ManageClubResponse> manageClub(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                         @PathVariable UUID id,
                                                         @RequestBody ManageClubCommand updateClubCommand){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        ManageClubResponse updateClubResponse = clubApplicationService.manageClub(token, id, updateClubCommand);
        return ResponseEntity.ok(updateClubResponse);
    }

}
