package com.cuemymusic.user.service.domain.dto.song.add;

import com.cuemymusic.user.service.domain.dto.song.query.QuerySongData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

public record AddSongResponse(QuerySongData song) { }
