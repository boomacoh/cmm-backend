package com.cuemymusic.club.service.domain.club.mapper.club;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.builder.ClubBuilder;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClubDataMapper {
    final private DeviceDataMapper deviceDataMapper;

    public ClubDataMapper(DeviceDataMapper deviceDataMapper) {
        this.deviceDataMapper = deviceDataMapper;
    }

    public Club addClubCommandToClub(CreateClubCommand createClubCommand, UserId adminId){

        return new ClubBuilder()
                .setAdminId(adminId)
                .setCountry(createClubCommand.country())
                .setCityName(createClubCommand.cityName())
                .setEmail(createClubCommand.email())
                .setName(createClubCommand.name())
                .setZipCode(createClubCommand.zipCode())
                .setPhoneNumber(createClubCommand.phoneNumber())
                .setStreetAddress(createClubCommand.streetAddress())
                .setAnnounce(false)
                .setMaxCoachPumps(10)
                .setMaxUserSongs(5)
                .setMaxCoachSongs(20)
                .createClub();
    }
    public Club updateClubCommandToClub(UpdateClubCommand updateClubCommand,UserId adminId){

        return new ClubBuilder()
                .setClubId(new ClubId(updateClubCommand.clubId()))
                .setAdminId(adminId)
                .setCountry(updateClubCommand.country())
                .setCityName(updateClubCommand.cityName())
                .setEmail(updateClubCommand.email())
                .setName(updateClubCommand.name())
                .setZipCode(updateClubCommand.zipCode())
                .setPhoneNumber(updateClubCommand.phoneNumber())
                .setStreetAddress(updateClubCommand.streetAddress())
                .createClub();
    }
    public ClubDTO clubToClubDTO(Club club){
        return  ClubDTO.builder()
                .clubId(club.getClubId().getValue())
                .name(club.getName())
                .cityName(club.getCityName())
                .country(club.getCountry())
                .zipCode(club.getZipCode())
                .email(club.getEmail())
                .streetAddress(club.getStreetAddress())
                .phoneNumber(club.getPhoneNumber())
                .devices(club.getDevices().stream().map(deviceDataMapper::deviceToUserDeviceDTO).toList())
                .maxCoachPumps(club.getMaxCoachPumps())
                .announce(club.getAnnounce())
                .maxCoachSongs(club.getMaxCoachSongs())
                .maxUserSongs(club.getMaxUserSongs())
                .build();
    }
    public ClubDTO.ClubDTOBuilder clubToClubDTOBuilder(Club club){
        return  ClubDTO.builder()
                .clubId(club.getClubId().getValue())
                .name(club.getName())
                .cityName(club.getCityName())
                .country(club.getCountry())
                .zipCode(club.getZipCode())
                .email(club.getEmail())
                .streetAddress(club.getStreetAddress())
                .phoneNumber(club.getPhoneNumber())
                .devices(club.getDevices().stream().map(deviceDataMapper::deviceToUserDeviceDTO).toList());
    }
    public CreateClubResponse clubToCreateClubResponse (Club club) {
        return CreateClubResponse.builder()
                .club(
                        this.clubToClubDTO(club)
                )
                .build();
    }
    public UpdateClubResponse clubToUpdateClubResponse (Club club) {
        return UpdateClubResponse.builder()
                .club(
                        this.clubToClubDTO(club)
                )
                .build();
    }

}

