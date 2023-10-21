package com.cuemymusic.firmware.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.TokenId;
import com.cuemymusic.user.service.domain.valueobject.TokenType;

public class Token {
    private TokenId id;

    private String token;

    private TokenType tokenType = TokenType.BEARER;

    private Boolean revoked;

    private Boolean expired;

    private User user;

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

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token(TokenId id, String token, TokenType tokenType, Boolean revoked, Boolean expired, User user) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id.getValue().toString().substring(0,6) + " , " +
                "expired=" + expired.toString() + " , " +
                "revoked=" + revoked.toString() + " , " +
                '}';
    }
}
