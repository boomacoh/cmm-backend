package com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub;

import com.cuemymusic.user.service.domain.valueobject.UserId;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateClubCommand(
        UUID clubId,
        String adminEmail,
        String name,
        String streetAddress,
        String cityName,
        String zipCode,
        String country,
        String email,
        String phoneNumber
){
}
