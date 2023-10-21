package com.cuemymusic.club.service.dataaccess.club.mapper;
import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import com.cuemymusic.club.service.dataaccess.device.entity.DeviceEntity;
import com.cuemymusic.club.service.dataaccess.device.mapper.DeviceDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.builder.ClubBuilder;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ClubDataAccessMapper {
    private DeviceDataAccessMapper deviceDataAccessMapper;
    private UserDataAccessMapper userDataAccessMapper;

    public ClubDataAccessMapper(UserDataAccessMapper userDataAccessMapper) {
        this.userDataAccessMapper = userDataAccessMapper;
    }

    public void setDeviceDataAccessMapper(DeviceDataAccessMapper deviceDataAccessMapper) {
        this.deviceDataAccessMapper = deviceDataAccessMapper;
    }


    public Club clubEntityToClubHelper(ClubEntity clubEntity){
        return new ClubBuilder()
                .setClubId(new ClubId(clubEntity.getId()))
                .setName(clubEntity.getName())
                .setStreetAddress(clubEntity.getStreetAddress())
                .setPhoneNumber(clubEntity.getPhoneNumber())
                .setZipCode(clubEntity.getZipCode())
                .setEmail(clubEntity.getEmail())
                .setCityName(clubEntity.getCityName())
                .setCountry(clubEntity.getCountry())
                .setAdminId(new UserId(clubEntity.getAdminId()))
                .setDisabledUsers(clubEntity.getDisabledUsers().stream().map(userDataAccessMapper::userEntityToUser).toList())
                .setMaxCoachSongs(clubEntity.getMaxCoachSongs())
                .setMaxUserSongs(clubEntity.getMaxUserSongs())
                .setMaxCoachPumps(clubEntity.getMaxCoachPumps())
                .setAnnounce(clubEntity.getAnnounce())
                .createClub();
    }
    public ClubEntity clubToClubEntityHelper(Club club){
        return ClubEntity.builder()
                .id(club.getClubId().getValue())
                .name(club.getName())
                .streetAddress(club.getStreetAddress())
                .phoneNumber(club.getPhoneNumber())
                .zipCode(club.getZipCode())
                .email(club.getEmail())
                .cityName(club.getCityName())
                .country(club.getCountry())
                .adminId(club.getAdminId().getValue())
                .disabledUsers(club.getDisabledUsers().stream().map(userDataAccessMapper::userToUserEntity).toList())
                .maxCoachPumps(club.getMaxCoachPumps())
                .maxUserSongs(club.getMaxUserSongs())
                .maxCoachSongs(club.getMaxCoachSongs())
                .announce(club.getAnnounce())
                .build();
    }
    public Club clubEntityToClub(ClubEntity clubEntity){
        final Club club = clubEntityToClubHelper(clubEntity);
        final List<Device> devices = clubEntity.getDevices().stream().map(e->{
            Device device = deviceDataAccessMapper.deviceEntityToDeviceHelper(e);
            device.setClub(club);
            return device;
        }).toList();
        club.setDevices(devices);
        return club;
    }
    public ClubEntity clubToClubEntity(Club club){
        final ClubEntity clubEntity = clubToClubEntityHelper(club);
        final List<DeviceEntity> deviceEntities = club.getDevices().stream().map( e -> {
                    DeviceEntity d = deviceDataAccessMapper.deviceToDeviceEntityHelper(e);
                    d.setClub(clubEntity);
                    return d;
                }).toList();
        clubEntity.setDevices(deviceEntities);
        return clubEntity;
    }


}
