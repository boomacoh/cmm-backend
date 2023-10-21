package com.cuemymusic.club.service.domain.club.dto.playlist.common;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PlaylistDTO (
  List<PlaylistSongData> songs
){}
