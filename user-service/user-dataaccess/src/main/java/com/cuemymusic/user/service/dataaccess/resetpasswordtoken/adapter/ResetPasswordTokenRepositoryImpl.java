package com.cuemymusic.user.service.dataaccess.resetpasswordtoken.adapter;

import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.repository.ResetPasswordTokenJpaRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.ResetPasswordTokenRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.entity.ResetPasswordTokenEntity;
import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.mapper.ResetPasswordTokenDataMapper;
import com.cuemymusic.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ResetPasswordTokenRepositoryImpl implements ResetPasswordTokenRepository {
    private final ResetPasswordTokenJpaRepository resetPasswordTokenJpaRepository;
    private final ResetPasswordTokenDataMapper resetPasswordTokenDataMapper;
    private final UserDataAccessMapper userDataAccessMapper;

    public ResetPasswordTokenRepositoryImpl(ResetPasswordTokenJpaRepository resetPasswordTokenJpaRepository, ResetPasswordTokenDataMapper resetPasswordTokenDataMapper, UserDataAccessMapper userDataAccessMapper) {
        this.resetPasswordTokenJpaRepository = resetPasswordTokenJpaRepository;
        this.resetPasswordTokenDataMapper = resetPasswordTokenDataMapper;
        this.userDataAccessMapper = userDataAccessMapper;
    }



    @Override
    public Optional<ResetPasswordToken> findByToken(String token) {
        return resetPasswordTokenJpaRepository.findByToken(token).map(
                e -> resetPasswordTokenDataMapper.resetPasswordTokenEntityToResetPasswordToken(
                        e,
                        userDataAccessMapper.userEntityToUser(e.getUser())
                )
        );
    }

    @Override
    public ResetPasswordToken save(ResetPasswordToken token) {
        ResetPasswordTokenEntity resetPasswordTokenEntity = resetPasswordTokenDataMapper.resetPasswordTokenToResetPasswordTokenEntity(
                token,
                userDataAccessMapper.userToUserEntity(token.getUser())
        );
        ResetPasswordTokenEntity result = resetPasswordTokenJpaRepository.save(resetPasswordTokenEntity);
        return resetPasswordTokenDataMapper.resetPasswordTokenEntityToResetPasswordToken(result,token.getUser());
    }



}
