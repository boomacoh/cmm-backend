package com.cuemymusic.user.service.application.rest;
import com.cuemymusic.user.service.domain.dto.authentication.password.change.ChangePasswordCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.dto.user.profile.UpdateUserProfileCommand;
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/profile", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasAnyRole('USER','ADMIN','COACH','MANAGER')")
@AllArgsConstructor
public class ProfileController {
    private final UserApplicationService userApplicationService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserProfileResponse> getInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        UserProfileResponse userProfileResponse = userApplicationService.getProfileInfo(token);
        return ResponseEntity.ok(userProfileResponse);
    }
    @GetMapping("/deactivate")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<FindUsersResponse> deactivate(){
        FindUsersResponse findUsersResponse = userApplicationService.findAll();
        return ResponseEntity.ok(findUsersResponse);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserProfileResponse> update(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody UpdateUserProfileCommand updateUserProfileCommand
    ){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        UserProfileResponse updateUserResponse = userApplicationService.updateProfileInfo(token,updateUserProfileCommand);
        return ResponseEntity.ok(updateUserResponse);
    }
    @PutMapping("/changePassword")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity changePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody ChangePasswordCommand changePasswordCommand
    ){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        userApplicationService.changePassword(token,changePasswordCommand);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/managers")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<FindUsersResponse> findManager(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        FindUsersResponse findUsersResponse = userApplicationService.findMyManagers(token);
        return ResponseEntity.ok(findUsersResponse);
    }
    @DeleteMapping("/devices/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,@PathVariable Integer id){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        userApplicationService.clearDevice(token,id);
        return ResponseEntity.ok().build();
    }
}
