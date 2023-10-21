package com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import lombok.Builder;

@Builder
public record UpdateClubResponse(ClubDTO club) {
}
