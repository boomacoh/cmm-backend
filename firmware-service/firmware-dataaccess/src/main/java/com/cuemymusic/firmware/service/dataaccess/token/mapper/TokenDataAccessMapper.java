package com.cuemymusic.firmware.service.dataaccess.token.mapper;


import com.cuemymusic.firmware.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.firmware.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.firmware.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.firmware.service.domain.entity.Token;
import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.firmware.service.domain.entity.builder.TokenBuilder;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenDataAccessMapper {
    private UserDataAccessMapper userDataAccessMapper;
    public void setUserDataAccessMapper(UserDataAccessMapper userDataAccessMapper) {
        this.userDataAccessMapper = userDataAccessMapper;
    }
    public Token tokenEntityToTokenHelper(TokenEntity tokenEntity){
        log.warn(tokenEntity.revoked.toString());
        return new TokenBuilder()
                .setToken(tokenEntity.getToken())
                .setId(new TokenId(tokenEntity.getId()))
                .setExpired(tokenEntity.getExpired())
                .setRevoked(tokenEntity.getRevoked())
                .setTokenType(tokenEntity.getTokenType())
                .createToken();
    }

    public TokenEntity tokenToTokenEntityHelper(Token token){
        log.warn(token.getId().getValue().toString());
        return new TokenEntity().builder()
                .id(token.getId().getValue())
                .token(token.getToken())
                .tokenType(token.getTokenType())
                .revoked(token.isRevoked())
                .expired(token.isExpired())
                .build();
    }
    public Token tokenEntityToToken(TokenEntity e) {
        Token t = tokenEntityToTokenHelper(e);
        User u = userDataAccessMapper.userEntityToUserHelper(e.getUser());
        u.setTokens(
                e.getUser().getTokens().stream().map(this::tokenEntityToTokenHelper).toList()
        );
        t.setUser(u);
        return t;
    }
    public TokenEntity tokenToTokenEntity(Token e) {
        TokenEntity t = tokenToTokenEntityHelper(e);
        UserEntity u = userDataAccessMapper.userToUserEntityHelper(e.getUser());
        u.setTokens(
                e.getUser().getTokens().stream().map(this::tokenToTokenEntityHelper).toList()
        );
        t.setUser(u);
        return t;
    }


}
