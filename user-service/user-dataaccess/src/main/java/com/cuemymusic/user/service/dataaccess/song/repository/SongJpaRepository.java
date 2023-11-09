package com.cuemymusic.user.service.dataaccess.song.repository;

import com.cuemymusic.user.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.user.service.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity,UUID> {


    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration,\n" +
            "u_f_s.user_id is NOT NULL as is_favorite\n" +
            "from users_songs u_s \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id and u_s.user_id = u_f_s.user_id \n" +
            "where u_s.user_id = ?1 and u_s.user_id = s.owner_id",
            nativeQuery = true
    )
    List<SongEntity> findOwnedMusic(UUID songId);
    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration,\n" +
            "u_f_s.user_id is NOT NULL as is_favorite\n" +
            "from users_songs u_s \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id  and u_s.user_id = u_f_s.user_id \n" +
            "where u_s.user_id = ?1 and u_s.user_id != s.owner_id",
            nativeQuery = true
    )
    List<SongEntity> findSharedMusic(UUID songId);
    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration,\n" +
            "u_f_s.user_id is NOT NULL as is_favorite\n" +
            "from users_songs u_s \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id and u_s.user_id = u_f_s.user_id \n" +
            "where u_s.user_id = ?1 and u_f_s.user_id is NOT NULL",
            nativeQuery = true
    )
    List<SongEntity> findFavoriteMusic(UUID userId);
    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration,\n" +
            "u_f_s.user_id is NOT NULL as is_favorite\n" +
            "from users_songs u_s \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id and u_s.user_id = u_f_s.user_id \n" +
            "where u_s.user_id = ?2 and s.id = ?1",
            nativeQuery = true
    )
    Optional<SongEntity> findSongById(UUID songId,UUID userId);



    @Modifying
    @Query(value = "INSERT INTO public.users_favorite_songs(\n" +
            "\tuser_id, song_id)\n" +
            "\tVALUES (?1, ?2);", nativeQuery = true)
    void setFavorite(UUID userId, UUID songId);
    @Modifying
    @Query(value = "DELETE FROM users_favorite_songs\n" +
            "\tWHERE user_id = ?1 and song_id = ?2", nativeQuery = true)
    void clearFavorite(UUID userId, UUID songId);

    List<SongEntity> findAllByCreatedByBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
