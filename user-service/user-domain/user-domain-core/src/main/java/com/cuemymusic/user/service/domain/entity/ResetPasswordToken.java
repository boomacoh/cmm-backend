package com.cuemymusic.user.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.TokenId;

import java.time.ZonedDateTime;

public class ResetPasswordToken {
    private static final int EXPIRATION = 60 * 24;
    private TokenId id;

    private String token;

    private User user;

    private ZonedDateTime expiryDate;

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    private Boolean revoked;

    public TokenId getId() {
        return id;
    }

    public void setId(TokenId id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ResetPasswordToken(TokenId id, String token, User user, ZonedDateTime expiryDate, Boolean revoked) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }

}
