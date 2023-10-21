package com.cuemymusic.club.service.domain.club.dto.playlist.moveSong;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MoveSongCommand(UUID songId,UUID addAfter) {

}
