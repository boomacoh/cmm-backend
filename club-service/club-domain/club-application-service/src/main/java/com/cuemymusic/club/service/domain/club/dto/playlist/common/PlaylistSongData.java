package com.cuemymusic.club.service.domain.club.dto.playlist.common;

import com.cuemymusic.club.service.domain.club.dto.common.UserDTO;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record PlaylistSongData(
    UUID id,
    SongData song,
    UserDTO user,
    ZonedDateTime eta,
    Boolean played
){}
