package com.cuemymusic.club.service.domain.club.dto.playlist.common;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SongData(
    UUID id,
    String name,
    String uploadName,
    String fileLocation,
    String copyRight,
    String title,
    String artist,
    String recordLabel,
    Integer duration
){

}
