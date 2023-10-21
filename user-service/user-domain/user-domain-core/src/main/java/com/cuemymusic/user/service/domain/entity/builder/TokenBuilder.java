package com.cuemymusic.user.service.domain.entity.builder;

import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import com.cuemymusic.user.service.domain.valueobject.TokenType;

public class TokenBuilder {
    private TokenId id;
    private String token;
    private TokenType tokenType;
    private Boolean revoked;
    private Boolean expired;
    private User user;

    public TokenBuilder setId(TokenId id) {
        this.id = id;
        return this;
    }

    public TokenBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenBuilder setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public TokenBuilder setRevoked(Boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public TokenBuilder setExpired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public TokenBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Token createToken() {
        return new Token(id, token, tokenType, revoked, expired, user);
    }
}