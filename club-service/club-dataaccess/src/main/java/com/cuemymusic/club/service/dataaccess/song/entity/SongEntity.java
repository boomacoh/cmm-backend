package com.cuemymusic.club.service.dataaccess.song.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
}
