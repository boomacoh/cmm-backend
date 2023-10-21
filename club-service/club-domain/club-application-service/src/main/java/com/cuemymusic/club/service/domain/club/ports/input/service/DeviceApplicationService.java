package com.cuemymusic.club.service.domain.club.ports.input.service;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.deleteDevice.DeleteDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.*;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.addSong.AddSongResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong.DeleteSongResponse;
import com.cuemymusic.club.service.domain.club.dto.playlist.moveSong.MoveSongCommand;
import com.cuemymusic.club.service.domain.club.dto.playlist.swapSongs.SwapSongsCommand;
import jakarta.validation.Valid;

import java.util.UUID;

public interface DeviceApplicationService {
    ////////////////////////////////////
    // CRUD Admin endpoints ############
    ////////////////////////////////////
    AddDeviceResponse createDevice(@Valid AddDeviceCommand addDeviceResponse);
    DeviceListResponse findAll(UUID clubId);
    DeviceListResponse findAll();
    UserDeviceListResponse findAllByUser();
    DeviceDTO findById(@Valid UUID id);
    UpdateDeviceResponse updateDevice(@Valid UpdateDeviceCommand updateDeviceCommand);
    DeleteDeviceResponse deleteDevice(@Valid UUID id);


    ////////////////////////////////////
    // Playlist endpoints   ############
    ////////////////////////////////////
    AddSongResponse addSong(@Valid String token, @Valid UUID id, @Valid AddSongCommand addSongCommand);
    DeleteSongResponse deleteSong(@Valid String token, @Valid UUID deviceId, @Valid DeleteSongCommand songId);
    PlaylistDTO getPlaylist(@Valid UUID id);
    PlaylistDTO pump(@Valid String token, UUID deviceId, UUID songId);

}
