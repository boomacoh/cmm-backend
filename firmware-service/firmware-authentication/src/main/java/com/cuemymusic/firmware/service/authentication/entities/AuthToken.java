package com.cuemymusic.firmware.service.authentication.entities;

import com.cuemymusic.user.service.domain.valueobject.TokenType;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
public class AuthToken {

    public UUID id;

    public String token;

    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    public AuthUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(id, authToken.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "id=" + id +
                '}';
    }
}