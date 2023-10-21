package com.cuemymusic.club.service.dataaccess.device.entity;

import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Table(name = "devices")
public class DeviceEntity {
    @Id
    private UUID id;
    private String name;
    private ZonedDateTime timeZone;
    private String wifiSsid;
    private String connectionInterface;
    private String wifiPassword;
    private String ipAddress;
    private Boolean isDhcp;
    private String subnet;
    private String gateway;
    private String dns;
    @OneToMany(cascade= {CascadeType.ALL,CascadeType.REMOVE})
    @JoinTable(
            name = "devices_playlist_song",
            joinColumns = { @JoinColumn(name = "device_id") },
            inverseJoinColumns = { @JoinColumn(name = "playlist_song_id") }
    )
    private List<PlaylistSongEntity> playlist;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private ClubEntity club;
    private Boolean enabled;
}
