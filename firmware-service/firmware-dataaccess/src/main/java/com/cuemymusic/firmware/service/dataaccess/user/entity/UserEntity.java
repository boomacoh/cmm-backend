package com.cuemymusic.firmware.service.dataaccess.user.entity;

import com.cuemymusic.firmware.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.user.service.domain.valueobject.Role;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "username")
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TokenEntity> tokens;
}
