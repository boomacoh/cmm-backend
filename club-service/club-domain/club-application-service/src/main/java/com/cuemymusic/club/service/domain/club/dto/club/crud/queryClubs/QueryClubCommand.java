package com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs;

import lombok.Builder;

import java.util.List;


@Builder
public record QueryClubCommand(
        String clubName,
        String address,
        String cityName,
        String zipCode,
        String phoneNumber,
        String email
) {}
