package com.cuemymusic.user.service.dataaccess.user.entity;

import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.entity.ResetPasswordTokenEntity;
import com.cuemymusic.user.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.user.service.dataaccess.song.entity.UserSongEntity;
import jakarta.persistence.*;
import lombok.*;
import com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;


import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    @Column(name="username")
    private String email;
    private Subscription subscription;
    private Integer enabled;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER,mappedBy = "user")
    private List<RankedDeviceEntity> devices;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_songs",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    private List<UserSongEntity> songs;

    private Role role;

    @OneToMany(mappedBy = "user")
    private List<ResetPasswordTokenEntity> resetPasswordTokenEntity;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TokenEntity> tokens;
    private UUID deviceId;
}
