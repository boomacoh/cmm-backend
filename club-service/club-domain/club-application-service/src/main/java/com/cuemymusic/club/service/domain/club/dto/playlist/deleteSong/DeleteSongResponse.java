package com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong;

import com.cuemymusic.club.service.domain.club.dto.playlist.common.SongData;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DeleteSongResponse(
        SongData deletedSong,
        Boolean success,
        Boolean forcePlay
) {
}
