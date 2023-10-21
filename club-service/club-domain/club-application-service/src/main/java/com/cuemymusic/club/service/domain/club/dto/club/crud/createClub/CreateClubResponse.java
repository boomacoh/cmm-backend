package com.cuemymusic.club.service.domain.club.dto.club.crud.createClub;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import lombok.Builder;

@Builder
public record CreateClubResponse (ClubDTO club) {
}
