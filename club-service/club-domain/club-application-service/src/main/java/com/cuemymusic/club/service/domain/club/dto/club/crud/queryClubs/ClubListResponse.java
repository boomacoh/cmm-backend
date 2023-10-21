package com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs;

import lombok.Builder;

import java.util.List;


@Builder
public record ClubListResponse(
    List<ClubDTO> clubs
) {

}
