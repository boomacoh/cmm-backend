package com.cuemymusic.user.service.application.rest;

import com.cuemymusic.user.service.application.service.FileStorage;
import com.cuemymusic.user.service.domain.dto.song.add.AddSongCommand;
import com.cuemymusic.user.service.domain.dto.song.add.AddSongResponse;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongCommand;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongResponse;
import com.cuemymusic.user.service.domain.dto.song.remove.RemoveSongResponse;
import com.cuemymusic.user.service.domain.dto.song.update.UpdateName;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserResponse;
import com.cuemymusic.user.service.domain.dto.user.favorite.clear.ClearFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.user.favorite.set.SetFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.user.profile.UpdateUserProfileCommand;
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping(value = "/api/songs")
@PreAuthorize("hasAnyRole('USER','ADMIN','COACH','MANAGER')")
@AllArgsConstructor
public class SongController {
    private final UserApplicationService userApplicationService;
    FileStorage storageService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<AddSongResponse> uploadSong (
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
        @RequestParam(name = "name", required = true) String name,
        @RequestParam("file") MultipartFile file
    ){
        final String token;
        log.warn("File Upload --------------->  : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        try {
            SongMetaData songMetaData = storageService.save(file);
            AddSongResponse  res =  userApplicationService.addSong(token, name, songMetaData);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.warn(e.getStackTrace().toString());
            throw new DomainException("Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<QuerySongResponse> getSongs(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestParam(name = "is_owned") Boolean isOwned,
            @RequestParam(name = "is_favorite") Boolean isFavorite
    ){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }

        token = authHeader.substring(7);
        QuerySongResponse querySongResponse =  userApplicationService.getSongs(token,isOwned,isFavorite);
        return ResponseEntity.ok(querySongResponse);
    }

    @GetMapping("/reports/usage")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<QuerySongResponse> findSongInfoByDateRange(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime
    ){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        log.info("=== date now " + LocalDateTime.now());
        log.info("=== " +startDateTime);
        log.info("=== " +endDateTime);
        token = authHeader.substring(7);
        QuerySongResponse querySongResponse =  userApplicationService
                .findSongInfoByDateRange(LocalDateTime.now().minusDays(7),LocalDateTime.now().plusDays(7));
        return ResponseEntity.ok(querySongResponse);
    }

    /**
     * 
     * @PostMapping("/usage")
     *     @PreAuthorize("hasAnyAuthority('admin:read')")
     *     public ResponseEntity getUsageReport(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
     *                                          @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime,
     *                                          @RequestParam String clubName, @RequestParam String rinkName, @RequestParam String userName) {
     *         log.info("AUTH HEADER PASSED " + authHeader);
     *         // For Usage Report.
     *         // Create a report that would show the
     *         // total number of times a song was played, -> plays 
     *         // who uploaded the song (username), -> username from user table
     *         // filename of song and copyright info. -> from song table -> name and path
     *
     *         // The Admin should be able to extract the data based on the time range given.
     * //        For Rink Admin, they are only limited to the Rink they are assigned to.
     * //        While for System Admin, they have the ability to create the report for all rink.
     * //
     * //        Note: A rink/club can have multiple Ice sheets.
     * //        e.g. : A rink/club can have a building with multiple sheets of ice.
     * //        For example Sioux Falls Figure Skating club has a building with 3 sheets of ice named Rink1, Rink2, Rink3.
     *         reportsApplicationService.findReportSongUsage(LocalDateTime.now().minusDays(7), LocalDateTime.now().plusDays(7),
     *                 clubName, rinkName, userName);
     *         return ResponseEntity.ok().build();
     *     }
     *
     *     @GetMapping
     *     public ResponseEntity getDeviceStatus() {
     * //        Device Status:
     * //        For Rink Admin: (the rink admin is in charge of the Rink, relationship is 1 rink is to 1 rink Admin)
     * //        The Rink Admin should be able to get all information of the associated RPI devices that are registered to the rink.
     * //                Here are the details that each RPI device should provide:
     * //        Status - online/offline,
     * //                wifi strength
     * //        free drive space
     * //        free memory
     * //        cpu usage
     * //        download speed
     * //        Please refer to the screenshot attached:
     * //
     * //        Note: The rink admin can only obtain device status of the rpi devices registered to its rink, and should not be able to see other devices on other rinks
     * //        For System Admin: (the system admin acts as the super user of the whole system. ideally only 1 account should be provided for the system admin, they have access to all rinks that are currently registered to the system)
     * //        For Device status, they can choose a club and get the list of devices connected to that club. and extract the same info as stated above for Rink Admin.
     *         return ResponseEntity.ok().build();
     *     }
     */
    
    @GetMapping("/{location}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity downloadSong(@PathVariable String location){
        Resource resource =  storageService.load(location);

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<RemoveSongResponse> deleteSong(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID id){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }

        token = authHeader.substring(7);
        RemoveSongResponse querySongResponse =  userApplicationService.removeSong(token,id);
        storageService.delete(querySongResponse.deletedSong().fileLocation());
        return ResponseEntity.ok(querySongResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity updateSong(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID id,
            @RequestBody UpdateName updateName){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        userApplicationService.updateName(token,id, updateName);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/favorite/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<SetFavoriteResponse> setFavorite(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID id){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }

        token = authHeader.substring(7);
        SetFavoriteResponse querySongResponse =  userApplicationService.setFavoriteSong(token,id);
        return ResponseEntity.ok(querySongResponse);
    }
    @DeleteMapping("/favorite/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<ClearFavoriteResponse> clearFavorite(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID id){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }

        token = authHeader.substring(7);
        ClearFavoriteResponse querySongResponse =  userApplicationService.clearFavoriteSong(token,id);
        return ResponseEntity.ok(querySongResponse);
    }
    @PostMapping("/{songId}/share")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity share(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @PathVariable UUID songId,
            @RequestParam(name = "userId") UUID userId){
        final String token;

        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }

        token = authHeader.substring(7);
        userApplicationService.share(token,songId,userId);
        return ResponseEntity.ok().build();
    }
}
