package com.cuemymusic.user.service.domain.dto.song.remove;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public record RemoveSongCommand(UUID songId, UUID userId) {}
