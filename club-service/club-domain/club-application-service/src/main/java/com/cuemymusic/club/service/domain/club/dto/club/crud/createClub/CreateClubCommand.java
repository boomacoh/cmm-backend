package com.cuemymusic.club.service.domain.club.dto.club.crud.createClub;

import com.cuemymusic.user.service.domain.valueobject.UserId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
public record CreateClubCommand (
        String adminEmail,
        String name,
        String streetAddress,
        String cityName,
        String zipCode,
        String country,
        String email,
        String phoneNumber,
        String contactPerson
){
}
