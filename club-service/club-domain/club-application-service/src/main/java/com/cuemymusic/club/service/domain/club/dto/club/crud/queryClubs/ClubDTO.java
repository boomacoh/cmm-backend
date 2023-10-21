package com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs;

import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.UserDeviceDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
public record ClubDTO (
        UUID clubId,
        String adminEmail,
        String name,
        String streetAddress,
        String cityName,
        String zipCode,
        String country,
        String email,
        String phoneNumber,
        List<UserDeviceDTO> devices,
        Boolean announce,
        Integer maxCoachPumps,
        Integer maxCoachSongs,
        Integer maxUserSongs
){
}
