package com.cuemymusic.user.service.dataaccess.resetpasswordtoken.mapper;

import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.entity.ResetPasswordTokenEntity;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.ResetPasswordTokenBuilder;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResetPasswordTokenDataMapper {
    public ResetPasswordToken resetPasswordTokenEntityToResetPasswordToken(ResetPasswordTokenEntity resetPasswordTokenEntity, User user){
        log.warn(resetPasswordTokenEntity.getRevoked().toString());
        return new ResetPasswordTokenBuilder()
                .setId(new TokenId(resetPasswordTokenEntity.getId()))
                .setToken(resetPasswordTokenEntity.getToken())
                .setExpiryDate(resetPasswordTokenEntity.getExpiryDate())
                .setUser(user)
                .setRevoked(resetPasswordTokenEntity.getRevoked())
                .createResetPasswordToken();
    }
    public ResetPasswordTokenEntity resetPasswordTokenToResetPasswordTokenEntity(ResetPasswordToken resetPasswordToken, UserEntity userEntity){
        return  ResetPasswordTokenEntity.builder()
                .id(resetPasswordToken.getId().getValue())
                .token(resetPasswordToken.getToken())
                .expiryDate(resetPasswordToken.getExpiryDate())
                .user(userEntity)
                .revoked(resetPasswordToken.getRevoked())
                .build();

    }
}
