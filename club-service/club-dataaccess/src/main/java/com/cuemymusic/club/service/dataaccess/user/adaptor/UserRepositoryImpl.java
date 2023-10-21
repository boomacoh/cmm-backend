package com.cuemymusic.club.service.dataaccess.user.adaptor;

import com.cuemymusic.club.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.club.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.user.repository.UserJpaRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
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




}
