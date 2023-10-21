package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.playlist;

import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.mapper.playlist.PlaylistDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.UUID;
@Component
public class GetPlaylistSongsHandler {
    final DeviceDataMapper deviceDataMapper;
    final PlaylistDataMapper playlistDataMapper;
    final DeviceRepository deviceRepository;

    public GetPlaylistSongsHandler(DeviceDataMapper deviceDataMapper, PlaylistDataMapper playlistDataMapper, DeviceRepository deviceRepository) {
        this.deviceDataMapper = deviceDataMapper;
        this.playlistDataMapper = playlistDataMapper;
        this.deviceRepository = deviceRepository;
    }
    public PlaylistDTO getPlaylist(UUID deviceId){
        if(!deviceRepository.isPresentById(deviceId)){
            throw new ClubDomainException("Device not found");
        }
        final Device device = deviceRepository.findById(deviceId);
        return playlistDataMapper.playlistToPlaylistDTO(
                device.getPlaylist().stream()
                        .sorted(
                                Comparator.comparingDouble(v -> v.getOrder())
                        )
                        .toList()
        );
    }
}
