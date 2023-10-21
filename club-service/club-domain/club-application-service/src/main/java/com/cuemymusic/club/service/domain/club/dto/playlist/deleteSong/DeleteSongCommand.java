package com.cuemymusic.club.service.domain.club.dto.playlist.deleteSong;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DeleteSongCommand(
        UUID songId
        
) {
}
