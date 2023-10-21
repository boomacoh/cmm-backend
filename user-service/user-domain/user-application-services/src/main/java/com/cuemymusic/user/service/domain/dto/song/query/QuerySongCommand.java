package com.cuemymusic.user.service.domain.dto.song.query;


import lombok.Builder;

@Builder
public record QuerySongCommand(Boolean isOwned){
}
