package com.cuemymusic.user.service.dataaccess.user.adaptor;

import com.cuemymusic.user.service.dataaccess.song.mapper.UserSongDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.user.service.dataaccess.user.mapper.DeviceDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.user.repository.UserJpaRepository;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.user.service.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;
    private final UserSongDataAccessMapper userSongDataAccessMapper;
    private final DeviceDataAccessMapper deviceDataAccessMapper;


    public UserRepositoryImpl(UserJpaRepository userJpaRepository,
                              UserDataAccessMapper userDataAccessMapper,
                              UserSongDataAccessMapper userSongDataAccessMapper,
                              DeviceDataAccessMapper deviceDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
        this.userSongDataAccessMapper = userSongDataAccessMapper;
        this.deviceDataAccessMapper = deviceDataAccessMapper;
    }

    @Override
    public User save(User user){
        UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);
        return userDataAccessMapper.userEntityToUser(
                userJpaRepository.save(userEntity)
        );
    }
    @Override
    public User findById(UUID userId) {
        UserEntity userEntity =  userJpaRepository.findById(userId).orElseThrow();
        User user = userDataAccessMapper.userEntityToUser(userEntity);
        return user;
    }
    @Override
    public User findByEmail(String email) {
        UserEntity userEntity =  userJpaRepository.findByEmail(email).orElseThrow();
        User user = userDataAccessMapper.userEntityToUser(userEntity);
        return user;
    }
    @Override
    public Boolean isPresentByEmail(String email) {
        return userJpaRepository.findByEmail(email).isPresent();
    }
    @Override
    public Boolean isPresentById(UUID id) {
        return userJpaRepository.findById(id).isPresent();
    }

    @Override
    public List<User> findMyManagers(UUID userId) {
        return userJpaRepository.findMyManagers(userId).stream().map(userDataAccessMapper::userEntityToUser).toList();
    }

    @Override
    public void clearAllDevices(UUID id) {
        userJpaRepository.deleteAllDevices(id);
    }

    @Override
    public void removeDevice(UUID userId, Integer rank) {
        userJpaRepository.deleteDevice(userId,rank);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(userDataAccessMapper::userEntityToUser).toList();
    }

    @Override
    public void deleteById(UUID userId){
        userJpaRepository.deleteById(userId);
    }



}
