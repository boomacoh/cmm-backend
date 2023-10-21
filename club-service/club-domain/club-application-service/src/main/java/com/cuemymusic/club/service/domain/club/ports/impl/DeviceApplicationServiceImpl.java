package com.cuemymusic.club.service.domain.club.ports.impl;

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
import com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud.*;
import com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.playlist.*;
import com.cuemymusic.club.service.domain.club.ports.input.service.DeviceApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeviceApplicationServiceImpl implements DeviceApplicationService {

    //////////////////////////////
    // Device CRUD HANDLERS
    /////////////////////////////
    final CreateDeviceHandler createDeviceHandler;
    final DeleteDeviceHandler deleteDeviceHandler;
    final FindDeviceByIdHandler findDeviceByIdHandler;
    final FindDevicesHandler findDevicesHandler;
    final UserFindDevicesHandler userFindDevicesHandler;
    final UpdateDeviceHandler updateDeviceHandler;

    //////////////////////////////
    // Playlist CRUD HANDLERS
    /////////////////////////////
    final AddSongHandler addSongHandler;
    final RemoveSongHandler removeSongHandler;
    final GetPlaylistSongsHandler getPlaylistSongsHandler;
    final SwapSongsHandler swapSongsHandler;

    @Override
    public AddDeviceResponse createDevice(AddDeviceCommand addDeviceCommand) {
        return createDeviceHandler.createDevice(addDeviceCommand);
    }

    @Override
    public DeviceListResponse findAll(UUID clubId) {
        return findDevicesHandler.findAll(clubId);
    }

    @Override
    public DeviceListResponse findAll() {
        return findDevicesHandler.findAll();
    }
    @Override
    public UserDeviceListResponse findAllByUser() {
        return userFindDevicesHandler.findAll();
    }

    @Override
    public DeviceDTO findById(UUID id) {
        return findById(id);
    }

    @Override
    public UpdateDeviceResponse updateDevice(UpdateDeviceCommand updateDeviceCommand) {
        return updateDeviceHandler.updateDevice(updateDeviceCommand);
    }

    @Override
    public DeleteDeviceResponse deleteDevice(UUID id) {
        return deleteDeviceHandler.deleteDevice(id);
    }

    @Override
    public AddSongResponse addSong(String token, UUID id, AddSongCommand addSongCommand) {
        return addSongHandler.addSong(token,id,addSongCommand);
    }

    @Override
    public DeleteSongResponse deleteSong(String token, UUID deviceId, DeleteSongCommand deleteSongCommand) {
        return removeSongHandler.removeSong(token,deviceId,deleteSongCommand);
    }


    @Override
    public PlaylistDTO getPlaylist(UUID id) {
        return getPlaylistSongsHandler.getPlaylist(id);
    }

    @Override
    public PlaylistDTO pump(String token, UUID deviceId, UUID songId) {
        return swapSongsHandler.pump(token, deviceId, songId);
    }

}
