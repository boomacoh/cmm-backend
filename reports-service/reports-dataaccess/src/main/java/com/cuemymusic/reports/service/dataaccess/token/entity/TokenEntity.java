package com.cuemymusic.reports.service.dataaccess.token.entity;

import com.cuemymusic.reports.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.domain.valueobject.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "token")
public class TokenEntity {
    @Id
    public UUID id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public Boolean revoked;

    public Boolean expired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
