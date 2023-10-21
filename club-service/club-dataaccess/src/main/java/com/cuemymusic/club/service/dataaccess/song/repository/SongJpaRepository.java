package com.cuemymusic.club.service.dataaccess.song.repository;

import com.cuemymusic.club.service.dataaccess.song.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SongJpaRepository extends JpaRepository<SongEntity,UUID> {


    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration\n" +
            "from users_songs u_s \n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "where u_s.user_id = ?1 and u_s.user_id = s.owner_id and s.id = ?2",
            nativeQuery = true
    )
    Optional<SongEntity> findSongById(UUID userId, UUID songId);

    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration\n" +
            "from users_songs u_s \n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "where u_s.user_id = ?1 and u_s.user_id = s.owner_id",
            nativeQuery = true
    )
    List<SongEntity> findOwnedMusic(UUID songId);
    @Query(value = "SELECT\n" +
            "s.id, s.name, s.file_location, \n" +
            "s.upload_name, s.copy_right, s.title,\n" +
            "s.artist, s.record_label, s.duration\n" +
            "from users_songs u_s \n" +
            "left join users_favorite_songs u_f_s \n" +
            "on u_s.song_id = u_f_s.song_id \n" +
            "left join songs s \n" +
            "on  u_s.song_id = s.id\n" +
            "where u_s.user_id = ?1 and u_s.user_id != s.owner_id",
            nativeQuery = true
    )
    List<SongEntity> findSharedMusic(UUID songId);
}
