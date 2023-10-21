package com.cuemymusic.user.service.domain.dto.song.query;

import lombok.Builder;

import java.util.List;


@Builder
public record QuerySongResponse(
    List<QuerySongData> songs
) {
}
