package com.cuemymusic.user.service.domain.entity.builder;

import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import com.cuemymusic.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class ResetPasswordTokenBuilder {
    private TokenId id;
    private String token;
    private User user;
    private ZonedDateTime expiryDate;

    private Boolean revoked;

    public ResetPasswordTokenBuilder setId(TokenId id) {
        this.id = id;
        return this;
    }
    public ResetPasswordTokenBuilder setRevoked(Boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public ResetPasswordTokenBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public ResetPasswordTokenBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public ResetPasswordTokenBuilder setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public ResetPasswordToken createResetPasswordToken() {
        return new ResetPasswordToken(id, token, user, expiryDate, revoked);
    }
}