package com.cuemymusic.club.service.dataaccess.club.entity;

import com.cuemymusic.club.service.dataaccess.device.entity.DeviceEntity;
import com.cuemymusic.club.service.dataaccess.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "club")
public class ClubEntity {
    @Id
    private UUID id;
    private UUID adminId;
    private String name;
    private String streetAddress;
    private String cityName;
    private String zipCode;
    private String country;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "club")
    private List<DeviceEntity> devices;
    @ManyToMany
    @JoinTable(
            name = "disabled_user_rink",
            joinColumns = { @JoinColumn(name = "club_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<UserEntity> disabledUsers;
    private Boolean announce;
    private Integer maxCoachPumps;
    private Integer maxCoachSongs;
    private Integer maxUserSongs;
}
