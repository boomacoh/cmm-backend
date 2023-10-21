package com.cuemymusic.user.service.dataaccess.song.repository;

import com.cuemymusic.user.service.dataaccess.song.entity.UserSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserSongJpaRepository extends JpaRepository<UserSongEntity, UUID> {

    @Modifying
    @Query(value = "DELETE FROM users_songs WHERE song_id=?1",nativeQuery = true)
    void revokeAllShares(UUID songId);
    @Modifying
    @Query(value = "DELETE FROM users_favorite_songs WHERE song_id=?1",nativeQuery = true)
    void revokeAllFavorites(UUID songId);
}
