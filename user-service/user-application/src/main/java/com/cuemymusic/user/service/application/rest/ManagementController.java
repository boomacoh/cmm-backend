package com.cuemymusic.user.service.application.rest;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleCommand;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleResponse;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/manager/users", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('MANAGER')")
@AllArgsConstructor
public class ManagementController {
    private final UserApplicationService userApplicationService;
    @PutMapping("/{clubId}/{userId}")
    @PreAuthorize("hasAuthority('management:update')")
    public ResponseEntity<ChangeRoleResponse> addCoach(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID clubId,
            @PathVariable UUID userId
    ){

        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);

        ChangeRoleResponse changeRoleResponse = userApplicationService.addCoach(token, clubId,userId);
        return ResponseEntity.ok(changeRoleResponse);
    }
    @DeleteMapping("/{clubId}/{userId}")
    @PreAuthorize("hasAuthority('management:update')")
    public ResponseEntity<ChangeRoleResponse> revokeCoach(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID clubId,
            @PathVariable UUID userId
    ){

        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);

        ChangeRoleResponse changeRoleResponse = userApplicationService.removeCoach(token, clubId,userId);
        return ResponseEntity.ok(changeRoleResponse);
    }
    @DeleteMapping("/{clubId}")
    @PreAuthorize("hasAuthority('management:update')")
    public ResponseEntity<ChangeRoleResponse> revokeAllCoaches(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID clubId
    ){

        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);

        userApplicationService.removeAllCoach(token, clubId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{clubId}")
    @PreAuthorize("hasAuthority('management:read')")
    public ResponseEntity<FindUsersResponse> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable UUID clubId){

        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);

        FindUsersResponse findUsersResponse = userApplicationService.findMyUsers(token, clubId);
        log.warn("Found Following Users : " + findUsersResponse.users() );
        return ResponseEntity.ok(findUsersResponse);
    }
    @GetMapping("/{clubId}/coaches")
    @PreAuthorize("hasAuthority('management:read')")
    public ResponseEntity<FindUsersResponse> findCoaches(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable UUID clubId){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);

        FindUsersResponse findUsersResponse = userApplicationService.findCoachesUsers(token, clubId);
        log.warn("Found Following Users : " + findUsersResponse.users() );
        return ResponseEntity.ok(findUsersResponse);
    }
}
