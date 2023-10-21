package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.moveSong.MoveSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.swapSongs.SwapSongsCommand;
import com.cuemymusic.club.service.domain.club.ports.input.service.DeviceApplicationService;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/playlist", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasAnyRole('USER','ADMIN','COACH','MANAGER')")
@RequiredArgsConstructor
public class PlaylistController {
    private final DeviceApplicationService deviceApplicationService;
    private final IMqttClient iMqttClient;
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PlaylistDTO> getInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,@PathVariable UUID id){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        PlaylistDTO playlist = deviceApplicationService.getPlaylist(id);

        return ResponseEntity.ok(playlist);
    }
    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<AddSongResponse> addSong(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                   @PathVariable UUID id,
                                                   @RequestBody AddSongCommand addSongCommand){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        AddSongResponse addSongResponse = deviceApplicationService.addSong(token,id,addSongCommand);
        refreshList(id,false);
        return ResponseEntity.ok(addSongResponse);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<DeleteSongResponse> deleteSong(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                                   @PathVariable UUID id,
                                                   @RequestBody DeleteSongCommand deleteSongCommand){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        DeleteSongResponse deleteSongResponse = deviceApplicationService.deleteSong(token,id,deleteSongCommand);
        refreshList(id,deleteSongResponse.forcePlay());
        return ResponseEntity.ok(deleteSongResponse);
    }
    @PostMapping("/{deviceId}/{songId}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PlaylistDTO> swap(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                            @PathVariable UUID deviceId,
                                            @PathVariable UUID songId){
        final String token;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new ClubDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        PlaylistDTO playlistDTO = deviceApplicationService.pump(token,deviceId,songId);
        refreshList(deviceId,false);
        return ResponseEntity.ok(playlistDTO);
    }

    void refreshList(UUID deviceId,Boolean forcePlay){
        String data;
        if(forcePlay){
            data = "forceStop";
        }else{
            data = "";
        }
        MqttMessage mqttMessage = new MqttMessage(data.getBytes());
        mqttMessage.setQos(2);
        mqttMessage.setRetained(true);
        try{
            iMqttClient.publish(deviceId.toString() + "/commands/load_playlist", mqttMessage);
        }catch (Exception e){
            log.warn("couldn't trigger playlist refresh");
        }

    }
}
