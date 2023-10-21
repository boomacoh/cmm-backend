package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.playlist;

import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.mapper.playlist.PlaylistDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.song.SongRepository;
import com.cuemymusic.club.service.domain.entity.*;
import com.cuemymusic.club.service.domain.entity.builder.PlaylistSongBuilder;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;
import com.cuemymusic.user.service.domain.valueobject.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AddSongHandler {
    final DeviceRepository deviceRepository;
    final DeviceDataMapper deviceDataMapper;
    final PlaylistDataMapper playlistDataMapper;
    final SongRepository songRepository;

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public AddSongHandler(DeviceRepository deviceRepository,
                          DeviceDataMapper deviceDataMapper,
                          PlaylistDataMapper playlistDataMapper,
                          SongRepository songRepository,
                          UserRepository userRepository,
                          AuthenticationService authenticationService) {
        this.deviceRepository = deviceRepository;
        this.deviceDataMapper = deviceDataMapper;
        this.playlistDataMapper = playlistDataMapper;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public AddSongResponse addSong(String token,UUID deviceId,AddSongCommand addSongCommand){
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        Optional<Song> song = songRepository.find(user.getUserId().getValue(), addSongCommand.songId());
        if(song.isEmpty()){
            throw new ClubDomainException("song doesn't exit");
        }
        if(!deviceRepository.isPresentById(deviceId)){
            throw new ClubDomainException("device doesn't exit");
        }
        Device device = deviceRepository.findById(deviceId);
        Boolean canAdd = false;
        if(user.getRole() == Role.USER){
            if(deviceRepository.countUserSongs(deviceId,user.getUserId().getValue()) < device.getClub().getMaxUserSongs()) {
                canAdd = true;
            }
        }else {
            if(deviceRepository.countUserSongs(deviceId,user.getUserId().getValue()) < device.getClub().getMaxCoachSongs()) {
                canAdd = true;
            }
        }
        if(device.getClub().getDisabledUsers().contains(user)){
            throw new ClubDomainException("user is disabled in this club");
        }
        Double x = deviceRepository.getLast(deviceId);
        if(x == null){
            x = 0.0;
        }
        if(canAdd){
            PlaylistSong playlistSong = new PlaylistSongBuilder()
                    .setPlayed(false)
                    .setUser(user)
                    .setSong(song.get())
                    .setId(new PlaylistSongId(UUID.randomUUID()))
                    .setOrder(Math.ceil(x)+1)
                    .createPlaylistSong();
            device.addSong(playlistSong);
            deviceRepository.save(device);
            return AddSongResponse.builder()
                    .success(true)
                    .build();
        }else{
            throw  new ClubDomainException("Exceeded maximum");
        }

    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new ClubDomainException("User with EMAIL: " + email + "already exists");
        }
    }
}
