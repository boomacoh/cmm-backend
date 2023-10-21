package com.cuemymusic.club.service.domain.club.dto.club.management;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ManageClubResponse(Boolean announce,Integer maxCoachPump, Integer maxUserSongs, Integer maxCoachSongs) {
}
