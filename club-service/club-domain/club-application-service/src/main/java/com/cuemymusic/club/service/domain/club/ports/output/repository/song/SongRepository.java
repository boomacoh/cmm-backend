package com.cuemymusic.club.service.domain.club.ports.output.repository.song;

import com.cuemymusic.club.service.domain.entity.Song;

import java.util.Optional;
import java.util.UUID;

public interface SongRepository {
    Optional<Song> find(UUID userId,UUID songId);

}
