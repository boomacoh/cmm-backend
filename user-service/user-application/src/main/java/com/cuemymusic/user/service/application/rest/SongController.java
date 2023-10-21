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
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
