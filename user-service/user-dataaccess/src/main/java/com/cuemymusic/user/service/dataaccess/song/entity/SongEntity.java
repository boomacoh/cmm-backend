package com.cuemymusic.user.service.dataaccess.song.entity;

import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "songs")
public class SongEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    private String name;
    private String uploadName;
    private String fileLocation;
    private String copyRight;
    private String title;
    private String artist;
    private String recordLabel;
    private Integer duration;
    private Boolean isFavorite;
}
