package com.cuemymusic.club.service.dataaccess.user.mapper;

import com.cuemymusic.club.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.club.service.dataaccess.token.mapper.TokenDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.club.service.domain.entity.Token;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserDataAccessMapper {
    private TokenDataAccessMapper tokenDataAccessMapper;

    public UserDataAccessMapper(TokenDataAccessMapper tokenDataAccessMapper) {
        this.tokenDataAccessMapper = tokenDataAccessMapper;
    }

    @PostConstruct
    public void init() {
        tokenDataAccessMapper.setUserDataAccessMapper(this);
    }

    public UserEntity userToUserEntityHelper(User user){

        return new UserEntity().builder()
                .id(user.getUserId().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    public User userEntityToUserHelper(UserEntity userEntity){
        log.warn(userEntity.getId().toString());
        return new UserBuilder()
                .setUserId(new UserId(userEntity.getId()))
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setEmail(userEntity.getEmail())
                .setRole(userEntity.getRole())
                .createUser();
    }
    public UserEntity userToUserEntity(User user){
        UserEntity u = userToUserEntityHelper(user);
        List<TokenEntity> tokenEntityList = user.getTokens().stream().map(e -> {
            final TokenEntity t = tokenDataAccessMapper.tokenToTokenEntityHelper(e);
            t.setUser(u);
            return t;
        }).toList();
        u.setTokens(tokenEntityList);
        return u;
    }
    public User userEntityToUser(UserEntity userEntity){
        User u = userEntityToUserHelper(userEntity);
        List<Token> tokenList = userEntity.getTokens().stream().map(e -> {
            final Token t = tokenDataAccessMapper.tokenEntityToTokenHelper(e);
            t.setUser(u);
            return t;
        }).toList();
        u.setTokens(tokenList);
        return u;
    }


}
