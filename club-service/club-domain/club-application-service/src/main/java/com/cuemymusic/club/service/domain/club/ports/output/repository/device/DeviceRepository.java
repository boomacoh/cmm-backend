package com.cuemymusic.club.service.domain.club.ports.output.repository.device;


import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.PlaylistSong;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {
    List<Device> findAll();
    Device findById(UUID deviceId);
    Device findByIdAndClubId(UUID clubId,UUID deviceId);
    List<Device> findAllByClubId(UUID deviceId);
    void delete(UUID deviceId);
    Device save(Device device);
    Boolean isPresentById(UUID deviceId);
    Double getLast(UUID deviceId);
//    Double getNextByCurrent(UUID deviceId, Double index);
    void pump(UUID deviceId, UUID songId);
    PlaylistSong findPlaylistSongById(UUID deviceId, UUID SongId);
    Boolean isSongPresentById(UUID deviceId, UUID songId);
    Integer countUserSongs(UUID deviceId, UUID userId);
    Integer countUserTodayPumps(UUID deviceId, UUID coachId);
    void logPush(UUID id, UUID userId, UUID deviceId, ZonedDateTime zonedDateTime);
    List<Device> findByUserAndClub(UUID userId, UUID clubId);
    public void addAdminToDevice(UUID adminId, UUID deviceId);
    public void removePlaylistSongById(UUID psId);

}
