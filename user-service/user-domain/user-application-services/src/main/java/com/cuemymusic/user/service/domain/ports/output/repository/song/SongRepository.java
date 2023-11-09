package com.cuemymusic.user.service.domain.ports.output.repository.song;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cuemymusic.user.service.domain.entity.Song;

public interface SongRepository {
    Song save(Song song);
    Optional<Song> findById(UUID songId, UUID userId);
    Optional<Song> findById(UUID songId);
    List<Song> findAll();
    List<Song> findOwnedMusic(UUID userId);
    List<Song> findSharedMusic(UUID userId);
    List<Song> findFavoriteMusic(UUID userId);
    void clearFavorite(UUID userId,UUID songId);
    void setFavorite(UUID userId,UUID songId);
    void deleteById(UUID songId);

    void revokeAllConnections(UUID songId);
    List<Song> findAllByCreatedByBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
