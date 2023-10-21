package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.playlist;

import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.mapper.playlist.PlaylistDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.UUID;

@Component
public class SwapSongsHandler {
    final DeviceDataMapper deviceDataMapper;
    final PlaylistDataMapper playlistDataMapper;
    final DeviceRepository deviceRepository;
    final AuthenticationService authenticationService;
    final UserRepository userRepository;

    public SwapSongsHandler(DeviceDataMapper deviceDataMapper,
                            PlaylistDataMapper playlistDataMapper,
                            DeviceRepository deviceRepository,
                            AuthenticationService authenticationService,
                            UserRepository userRepository) {
        this.deviceDataMapper = deviceDataMapper;
        this.playlistDataMapper = playlistDataMapper;
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }
    @Transactional
    public PlaylistDTO pump(String token, UUID deviceId, UUID songId){
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        if(!deviceRepository.isPresentById(deviceId)){
            throw new ClubDomainException("Device not found");
        }
        final Device device = deviceRepository.findById(deviceId);
        if(deviceRepository.countUserTodayPumps(deviceId,user.getUserId().getValue()) > device.getClub().getMaxCoachPumps()){
            throw new ClubDomainException("Exceeded limit");
        }
        deviceRepository.pump(deviceId,songId);
        deviceRepository.logPush(UUID.randomUUID(),user.getUserId().getValue(),deviceId, ZonedDateTime.now());
        return playlistDataMapper.playlistToPlaylistDTO(
                device.getPlaylist().stream()
                        .sorted(
                                Comparator.comparingDouble(v -> v.getOrder())
                        )
                        .toList()
        );
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new ClubDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
