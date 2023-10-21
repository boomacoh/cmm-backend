package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.playlist;

import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.mapper.playlist.PlaylistDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.song.SongRepository;
import com.cuemymusic.club.service.domain.entity.*;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;
import com.cuemymusic.user.service.domain.valueobject.Role;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class RemoveSongHandler {
    final DeviceRepository deviceRepository;
    final DeviceDataMapper deviceDataMapper;
    final PlaylistDataMapper playlistDataMapper;
    final SongRepository songRepository;

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public RemoveSongHandler(DeviceRepository deviceRepository,
                          DeviceDataMapper deviceDataMapper,
                          PlaylistDataMapper playlistDataMapper,
                          SongRepository songRepository, UserRepository userRepository, AuthenticationService authenticationService) {
        this.deviceRepository = deviceRepository;
        this.deviceDataMapper = deviceDataMapper;
        this.playlistDataMapper = playlistDataMapper;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public DeleteSongResponse removeSong(String token, UUID deviceId,DeleteSongCommand deleteSongCommand){
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        if(!deviceRepository.isPresentById(deviceId)){
            throw new ClubDomainException("device doesn't exit");
        }
        Device device = deviceRepository.findById(deviceId);
        PlaylistSong song = device.getSong(new PlaylistSongId(deleteSongCommand.songId()));
        if (song == null) throw new ClubDomainException("Playlist Song with id " + user.getUserId().getValue() + " doesn't exist");
        if(user.getRole() == Role.USER && !user.getUserId().getValue().equals(song.getUser().getUserId().getValue())){
            throw new ClubDomainException("deletion is not authorized for user " + user.getUserId().getValue());
        }
        Boolean forcePlay = song.getId().getValue().equals(device.getPlaylist().get(0).getId().getValue());
        device.removeSong(song);
        deviceRepository.save(device);
        deviceRepository.removePlaylistSongById(song.getId().getValue());
        return DeleteSongResponse.builder()
                .deletedSong(playlistDataMapper.songToSongData(song.getSong()))
                .success(true)
                .forcePlay(forcePlay)
                .build();
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new ClubDomainException("User with EMAIL: " + email + "already exists");
        }
    }
}
