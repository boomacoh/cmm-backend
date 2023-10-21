package com.cuemymusic.club.service.domain.club.dto.playlist.addSong;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AddSongCommand(
        UUID songId
) {
}
