package com.cuemymusic.user.service.dataaccess.resetpasswordtoken.repository;

import com.cuemymusic.user.service.dataaccess.resetpasswordtoken.entity.ResetPasswordTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResetPasswordTokenJpaRepository extends JpaRepository<ResetPasswordTokenEntity, UUID> {

    Optional<ResetPasswordTokenEntity> findByToken(String token);
    ResetPasswordTokenEntity save(ResetPasswordTokenEntity token);
}
