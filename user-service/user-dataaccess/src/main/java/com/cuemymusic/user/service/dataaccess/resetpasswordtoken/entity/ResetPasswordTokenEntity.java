package com.cuemymusic.user.service.dataaccess.resetpasswordtoken.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;

import java.time.ZonedDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Table(name="password_reset_token")
@Entity
@Data
@Builder
public class ResetPasswordTokenEntity {
    private static final int EXPIRATION = 60 * 24;

    @Id
    private UUID id;

    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Boolean revoked;

    private ZonedDateTime expiryDate;
}
