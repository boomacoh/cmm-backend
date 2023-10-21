package com.cuemymusic.user.service.dataaccess.user.mapper;

import com.cuemymusic.user.service.dataaccess.song.mapper.UserSongDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.token.mapper.TokenDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.user.entity.DeviceEntity;
import com.cuemymusic.user.service.dataaccess.user.entity.RankedDeviceEntity;
import com.cuemymusic.user.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserDataAccessMapper {
    final UserSongDataAccessMapper userSongDataAccessMapper;
    final TokenDataAccessMapper tokenDataAccessMapper;
    final DeviceDataAccessMapper deviceDataAccessMapper;
    @PostConstruct
    public void init() {
        tokenDataAccessMapper.setUserDataAccessMapper(this);
        deviceDataAccessMapper.setUserDataAccessMapper(this);
    }
    public UserDataAccessMapper(UserSongDataAccessMapper userSongDataAccessMapper,
                                TokenDataAccessMapper tokenDataAccessMapper,
                                DeviceDataAccessMapper deviceDataAccessMapper) {
        this.userSongDataAccessMapper = userSongDataAccessMapper;
        this.tokenDataAccessMapper = tokenDataAccessMapper;
        this.deviceDataAccessMapper = deviceDataAccessMapper;
    }

    public UserEntity userToUserEntityHelper(User user){

        return new UserEntity().builder()
                .id(user.getId().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .subscription(user.getSubscription())
                .role(user.getRole())
                .enabled(user.getEnabled() ? 1: 0)
                .password(user.getPassword())
                .songs(user.getSongs().stream().map(userSongDataAccessMapper::songToSongEntity).toList())
                .build();
    }
    public User userEntityToUserHelper(UserEntity userEntity){
        log.warn(userEntity.getId().toString());
        return new UserBuilder()
                .setId(new UserId(userEntity.getId()))
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setEmail(userEntity.getEmail())
                .setSubscription(userEntity.getSubscription())
                .setRole(userEntity.getRole())
                .setEnabled(userEntity.getEnabled() == 1)
                .setPassword(userEntity.getPassword())
                .setSongs(userEntity.getSongs().stream().map(userSongDataAccessMapper::songEntityToSong).toList())
                .setDeviceId(new DeviceId(userEntity.getDeviceId()))
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
        u.setDevices(user.getDevices().stream().map(e->{
            RankedDeviceEntity rde = deviceDataAccessMapper.rankedDeviceToRankedDeviceEntityHelper(e);
            rde.setUser(u);
            return rde;
        }).toList());
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
        u.setDevices(userEntity.getDevices().stream().map(e -> {
            final RankedDevice r = deviceDataAccessMapper.rankedDeviceEntityToRankedDeviceHelper(e);
            r.setUser(u);
            return r;
        }).toList());
        return u;
    }
}
