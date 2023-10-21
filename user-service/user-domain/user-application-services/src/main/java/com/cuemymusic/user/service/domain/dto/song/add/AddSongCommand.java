package com.cuemymusic.user.service.domain.dto.song.add;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;



@Builder
public record AddSongCommand(@NotNull String name) {}
