package com.cuemymusic.club.service.dataaccess.device.adaptor;

import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import com.cuemymusic.club.service.dataaccess.club.mapper.ClubDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.device.entity.DeviceEntity;
import com.cuemymusic.club.service.dataaccess.device.entity.PlaylistSongEntity;
import com.cuemymusic.club.service.dataaccess.device.mapper.DeviceDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.device.repository.DeviceJpaRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.PlaylistSong;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DeviceRepositoryImpl implements DeviceRepository {
    final DeviceJpaRepository deviceJpaRepository;
    final DeviceDataAccessMapper deviceDataAccessMapper;
    final ClubDataAccessMapper clubDataAccessMapper;

    public DeviceRepositoryImpl(DeviceJpaRepository deviceJpaRepository,
                                DeviceDataAccessMapper deviceDataAccessMapper,
                                ClubDataAccessMapper clubDataAccessMapper) {
        this.deviceJpaRepository = deviceJpaRepository;
        this.deviceDataAccessMapper = deviceDataAccessMapper;
        this.clubDataAccessMapper = clubDataAccessMapper;
    }

    @Override
    public List<Device> findAll() {
        return deviceJpaRepository.findAll().stream().map(deviceDataAccessMapper::deviceEntityToDevice).toList();
    }

    @Override
    public Device findById(UUID deviceId) {
        final DeviceEntity deviceEntity = deviceJpaRepository.findById(deviceId).get();
        return deviceDataAccessMapper.deviceEntityToDevice(deviceEntity);
    }

    @Override
    public Device findByIdAndClubId(UUID clubId, UUID deviceId) {
        final DeviceEntity deviceEntity = deviceJpaRepository.findDevicesByIdAndClub_Id(deviceId,clubId).get();
        return deviceDataAccessMapper.deviceEntityToDevice(deviceEntity);
    }

    @Override
    public List<Device> findAllByClubId(UUID clubId) {
        final List<DeviceEntity> deviceEntities = deviceJpaRepository.findDevicesByClub_Id(clubId);
        final List<Device> devices = deviceEntities.stream().map(deviceDataAccessMapper::deviceEntityToDevice).toList();
        return devices;
    }

    @Override
    public void delete(UUID deviceId) {
        deviceJpaRepository.deleteDeviceLinks(deviceId);
        deviceJpaRepository.deleteById(deviceId);
    }

    @Override
    public Device save(Device device) {
        final DeviceEntity deviceEntity = deviceDataAccessMapper.deviceToDeviceEntity(device);
        final DeviceEntity result =  deviceJpaRepository.save(deviceEntity);
        return deviceDataAccessMapper.deviceEntityToDevice(result);
    }

    @Override
    public Boolean isPresentById(UUID deviceId) {
        return deviceJpaRepository.findById(deviceId).isPresent();
    }

    @Override
    public Double getLast(UUID deviceId) {
        return deviceJpaRepository.getLast(deviceId);
    }

    @Override
    public void pump(UUID deviceId, UUID songId) {
        if(!deviceJpaRepository.demote(deviceId, songId).isEmpty()){
            deviceJpaRepository.promote(deviceId, songId);
        }
    }

    @Override
    public PlaylistSong findPlaylistSongById(UUID deviceId, UUID SongId) {
        return null;
    }

    @Override
    public Boolean isSongPresentById(UUID deviceId, UUID songId) {
        return null;
    }
    @Override
    public Integer countUserSongs(UUID deviceId, UUID userId) {
        return deviceJpaRepository.countSongsByUserID(deviceId, userId);
    }

    @Override
    public Integer countUserTodayPumps(UUID deviceId, UUID coachId) {
        return deviceJpaRepository.countUserTodayPushes(deviceId,coachId);
    }

    @Override
    public void logPush(UUID id, UUID userId, UUID deviceId, ZonedDateTime zonedDateTime) {
        deviceJpaRepository.logPush(id,userId,deviceId,zonedDateTime);
    }

    @Override
    public List<Device> findByUserAndClub(UUID userId, UUID clubId) {
        final List<DeviceEntity> deviceEntities = deviceJpaRepository.findDevicesByClubIdAndUserId(clubId,userId);
        final List<Device> devices = deviceEntities.stream().map(deviceDataAccessMapper::deviceEntityToDevice).toList();
        return devices;
    }

    public void addAdminToDevice(UUID adminId, UUID deviceId){
        deviceJpaRepository.addAdminToDevice(adminId,deviceId, UUID.randomUUID());
    }

    @Override
    public void removePlaylistSongById(UUID psId) {
        deviceJpaRepository.deletePlaylistSong(psId);
    }

}
