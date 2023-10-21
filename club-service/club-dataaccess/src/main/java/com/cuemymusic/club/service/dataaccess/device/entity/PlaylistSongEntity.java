package com.cuemymusic.club.service.dataaccess.device.entity;

import com.cuemymusic.club.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.club.service.dataaccess.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "playlist_song")
public class PlaylistSongEntity {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name="song_id", nullable=false)
    private SongEntity songEntity;
    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;
    private ZonedDateTime eta;
    private Boolean played;
    @Column(name = "order_in_list")
    private Double order;
}
