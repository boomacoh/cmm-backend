package com.cuemymusic.club.service.dataaccess.device.repository;

import com.cuemymusic.club.service.dataaccess.device.entity.DeviceEntity;
import com.cuemymusic.club.service.dataaccess.device.entity.PlaylistSongEntity;
import com.cuemymusic.club.service.dataaccess.song.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DeviceJpaRepository extends JpaRepository<DeviceEntity,UUID> {
    Optional<DeviceEntity> findDevicesByIdAndClub_Id(UUID deviceId,UUID clubId);
    List<DeviceEntity> findDevicesByClub_Id(UUID clubId);
    @Query(value = "SELECT d.* " +
            "from devices d " +
            "inner join club c on d.club_id = c.id " +
            "inner join ranked_device rd on rd.device_id = d.id " +
            "where c.id = ?1 and rd.user_id = ?2  " +
            "order by rd.rank", nativeQuery = true)
    List<DeviceEntity> findDevicesByClubIdAndUserId(UUID clubId, UUID userID);
    @Query(
            value ="Select AVG(v.order) " +
                    "from ( " +
                    "Select p.order " +
                    "from playlist_song p inner join devices_playlist_song d " +
                    "where p.order_in_list >= ?2 and d.device_id = ?1 " +
                    "LIMIT 2 ) v;",
            nativeQuery = true
    )
    Double getNextByCurrent(UUID deviceId, Double index);
    @Query(
            value ="Select Max(pd.order_in_list) " +
                    "from ( " +
                    "select p.order_in_list " +
                    "from playlist_song p inner join devices_playlist_song d " +
                    "on p.id = d.playlist_song_id " +
                    "where d.device_id = ?1) pd;",
            nativeQuery = true
    )
    Double getLast(UUID deviceId);
    @Modifying
    @Query(value =
            "UPDATE public.playlist_song ps1\n" +
                    "SET order_in_list = ps2.order_in_list\n" +
                    "FROM (\n" +
                    "\tSELECT ps.id, dps.device_id,ps.order_in_list,\n" +
                    "\tlag(ps.id) over (order by ps.order_in_list desc) as prev \n" +
                    "\tFROM  public.playlist_song ps\n" +
                    "\tinner join public.devices_playlist_song dps\n" +
                    "\ton ps.id = dps.playlist_song_id\n" +
                    "\twhere dps.device_id = ?1 \n" +
                    ") ps2 \n" +
                    "WHERE ps2.id = ?2 and ps2.prev = ps1.id\n" +
                    "RETURNING ps2.id;"
            ,nativeQuery = true)
    List<UUID> demote(UUID deviceId,UUID songId);
    @Modifying
    @Query(value =
            "UPDATE public.playlist_song ps\n" +
            "SET order_in_list = ps.order_in_list + 1\n" +
            "FROM public.devices_playlist_song dps\n" +
            "WHERE id = ?2 \n" +
            "and dps.device_id = ?1 ;"
            ,nativeQuery = true)
    void promote(UUID deviceId,UUID songId);
    Optional<PlaylistSongEntity> findDevices_PlaylistById(UUID playlistSongId);
    @Query(value = "SELECT count(ps.id)\n" +
            "from playlist_song ps\n" +
            "inner join devices_playlist_song dps\n" +
            "on ps.id = dps.playlist_song_id\n" +
            "where dps.device_id = ?1 \n" +
            "and ps.user_id = ?2 \n" +
            "and ps.played = false",
            nativeQuery = true
    )
    Integer countSongsByUserID(UUID deviceID, UUID userId);
    @Query(value = "SELECT COUNT(id) from user_pushes up " +
            "WHERE DATE(up.time AT TIME ZONE 'UTC') = DATE(NOW() AT TIME ZONE 'UTC') " +
            "AND user_id = ?2 " +
            "AND device_id = ?1",
            nativeQuery = true
    )
    Integer countUserTodayPushes(UUID deviceId, UUID userId);
    @Modifying
    @Query(value = "INSERT INTO public.user_pushes(\n" +
            "id, user_id, \"time\", device_id)\n" +
            "VALUES (?1, ?2, ?4, ?3);",
            nativeQuery = true
    )
    void logPush(UUID id, UUID userId, UUID deviceId, ZonedDateTime time);
    @Modifying
    @Query(value = "INSERT INTO public.ranked_device(\n" +
            "\tuser_id, device_id, rank)\n" +
            "\tVALUES (?1, ?2, -1);",
            nativeQuery = true
    )
    void addAdminToDevice(UUID adminId, UUID deviceId, UUID rankedId);
    @Modifying
    @Query(value = "DELETE FROM public.ranked_device rd where rd.device_id = ?1 ",
            nativeQuery = true
    )
    void deleteDeviceLinks(UUID deviceId);
    @Modifying
    @Query(value = "DELETE FROM public.playlist_song where id = ?1 ",
            nativeQuery = true
    )
    void deletePlaylistSong(UUID playlistSongID);
}
