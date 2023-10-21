package com.cuemymusic.user.service.domain.dto.song.query;

import lombok.Builder;
import java.util.UUID;

@Builder
public record QuerySongData (
    UUID id,
    String name,
    String uploadName,
    String fileLocation,
    String copyRight,
    String title,
    String artist,
    String recordLabel,
    Integer duration,
    Boolean isFavorite
){

}
