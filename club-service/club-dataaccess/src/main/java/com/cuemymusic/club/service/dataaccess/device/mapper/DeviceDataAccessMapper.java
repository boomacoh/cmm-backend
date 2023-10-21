package com.cuemymusic.club.service.dataaccess.device.mapper;

import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import com.cuemymusic.club.service.dataaccess.club.mapper.ClubDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.device.entity.DeviceEntity;
import com.cuemymusic.club.service.dataaccess.device.entity.PlaylistSongEntity;
import com.cuemymusic.club.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.club.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.club.service.domain.entity.*;
import com.cuemymusic.club.service.domain.entity.builder.DeviceBuilder;
import com.cuemymusic.club.service.domain.entity.builder.PlaylistSongBuilder;
import com.cuemymusic.club.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DeviceDataAccessMapper {
    private UserDataAccessMapper userDataAccessMapper;
    private ClubDataAccessMapper clubDataAccessMapper;

    public DeviceDataAccessMapper(UserDataAccessMapper userDataAccessMapper, ClubDataAccessMapper clubDataAccessMapper) {
        this.userDataAccessMapper = userDataAccessMapper;
        this.clubDataAccessMapper = clubDataAccessMapper;
    }
    @PostConstruct
    public void init() {
        clubDataAccessMapper.setDeviceDataAccessMapper(this);
    }

    public Song songEntityToSong(SongEntity songEntity){
        return new SongBuilder()
                .setSongId(new SongId(songEntity.getId()))
                .setName(songEntity.getName())
                .setFileLocation(songEntity.getFileLocation())
                .setArtist(songEntity.getArtist())
                .setCopyRight(songEntity.getCopyRight())
                .setDuration(songEntity.getDuration())
                .setRecordLabel(songEntity.getRecordLabel())
                .setTitle(songEntity.getTitle())
                .setUploadName(songEntity.getUploadName())
                .createSong();

    }
    public PlaylistSong playlistSongEntityToPlaylistSong(PlaylistSongEntity playlistSongEntity){
        return new PlaylistSongBuilder()
                .setId(new PlaylistSongId(playlistSongEntity.getId()))
                .setSong(
                        songEntityToSong(playlistSongEntity.getSongEntity())
                )
                .setUser(
                        userDataAccessMapper.userEntityToUser(playlistSongEntity.getUserEntity())
                )
                .setEta(playlistSongEntity.getEta())
                .setPlayed(playlistSongEntity.getPlayed())
                .setOrder(playlistSongEntity.getOrder())
                .createPlaylistSong();
    }
    public Device deviceEntityToDeviceHelper(DeviceEntity deviceEntity){
        Device device = new DeviceBuilder()
                .setId(new DeviceId(deviceEntity.getId()))
                .setConnectionInterface(deviceEntity.getConnectionInterface())
                .setDns(deviceEntity.getDns())
                .setEnabled(deviceEntity.getEnabled())
                .setGateway(deviceEntity.getGateway())
                .setIpAddress(deviceEntity.getIpAddress())
                .setIsDhcp(deviceEntity.getIsDhcp())
                .setSubnet(deviceEntity.getSubnet())
                .setWifiPassword(deviceEntity.getWifiPassword())
                .setWifiSsid(deviceEntity.getWifiSsid())
                .setZonedDateTime(deviceEntity.getTimeZone())
                .setName(deviceEntity.getName())
                .setPlaylist(deviceEntity.getPlaylist().stream().map(this::playlistSongEntityToPlaylistSong).toList())
                .createDevice();
        return device;
    }



    public SongEntity songEntityToSong(Song song){
        return SongEntity.builder()
                .id(song.getId().getValue())
                .name(song.getName())
                .fileLocation(song.getFileLocation())
                .artist(song.getArtist())
                .copyRight(song.getCopyRight())
                .duration(song.getDuration())
                .recordLabel(song.getRecordLabel())
                .title(song.getTitle())
                .uploadName(song.getUploadName())
                .build();
    }
    public PlaylistSongEntity playlistSongToPlaylistSongEntity(PlaylistSong playlistSong){
        return PlaylistSongEntity.builder()
                .id(playlistSong.getId().getValue())
                .songEntity(
                        songEntityToSong(playlistSong.getSong())
                )
                .userEntity(
                        userDataAccessMapper.userToUserEntity(playlistSong.getUser())
                )
                .eta(playlistSong.getEta())
                .played(playlistSong.getPlayed())
                .order(playlistSong.getOrder())
                .build();
    }

    public DeviceEntity deviceToDeviceEntityHelper(Device device){
        DeviceEntity deviceEntity = DeviceEntity.builder()
                .id(device.getId().getValue())
                .connectionInterface(device.getConnectionInterface())
                .dns(device.getDns())
                .enabled(device.getEnabled())
                .gateway(device.getGateway())
                .ipAddress(device.getIpAddress())
                .isDhcp(device.getDhcp())
                .subnet(device.getSubnet())
                .wifiPassword(device.getWifiPassword())
                .wifiSsid(device.getWifiSsid())
                .timeZone(device.getZonedDateTime())
                .name(device.getName())
                .playlist(device.getPlaylist().stream().map(this::playlistSongToPlaylistSongEntity).toList())
                .build();
        return deviceEntity;
    }
    public Device deviceEntityToDevice(DeviceEntity deviceEntity){
        final Device device = deviceEntityToDeviceHelper(deviceEntity);
        final Club club = clubDataAccessMapper.clubEntityToClubHelper(deviceEntity.getClub());
        device.setClub(club);
        final List<PlaylistSong> playlistSongs = deviceEntity.getPlaylist().stream().map(this::playlistSongEntityToPlaylistSong).toList();
        device.setPlaylist(playlistSongs);
        return device;
    }
    public DeviceEntity deviceToDeviceEntity(Device device){
        final DeviceEntity deviceEntity = deviceToDeviceEntityHelper(device);
        final ClubEntity clubEntity = clubDataAccessMapper.clubToClubEntityHelper(device.getClub());
        final List<PlaylistSongEntity> playlistSongs = device.getPlaylist().stream().map(this::playlistSongToPlaylistSongEntity).toList();
        deviceEntity.setClub(clubEntity);
        deviceEntity.setPlaylist(playlistSongs);
        return deviceEntity;
    }
}
