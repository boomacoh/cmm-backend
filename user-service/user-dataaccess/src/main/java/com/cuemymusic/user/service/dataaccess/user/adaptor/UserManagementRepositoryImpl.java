package com.cuemymusic.user.service.dataaccess.user.adaptor;

import com.cuemymusic.user.service.dataaccess.user.repository.UserJpaRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.management.UserManagerRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserManagementRepositoryImpl implements UserManagerRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserManagementRepositoryImpl(UserJpaRepository userJpaRepository,
                                        UserDataAccessMapper userDataAccessMapper
                                        ) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Override
    public User save(User user){
        UserEntity userEntity = userDataAccessMapper.userToUserEntity(user);
        return userDataAccessMapper.userEntityToUser(
                userJpaRepository.save(userEntity)
        );
    }

    @Override
    public Boolean isMyUserPresent(UUID adminId, UUID id, UUID clubId) {
        return userJpaRepository.findUserByClubId(adminId,id,clubId).isPresent();
    }

    @Override
    public List<User> findMyUsers(UUID adminId, UUID clubId) {
        return userJpaRepository.findAllMyUsersByClubId(adminId,clubId).stream().map(userDataAccessMapper::userEntityToUser).toList();
    }

    @Override
    public User findMyUserById(UUID adminID, UUID userId, UUID clubId) {
        return userJpaRepository.findUserByClubId(adminID,userId,clubId).map(userDataAccessMapper::userEntityToUser).get();
    }

    @Override
    public void assignCoach(UUID clubId, UUID userId) {
        userJpaRepository.assignUser(userId,clubId);
    }

    @Override
    public void revokeCoach(UUID clubId, UUID userId) {
        userJpaRepository.revokeUser(userId,clubId);
    }

    @Override
    public void revokeAllCoaches(UUID adminId, UUID clubId) {
        userJpaRepository.setUserByClubId(clubId);
        userJpaRepository.revokeAllUsersByClubId(clubId);
    }


}
