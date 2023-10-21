package com.cuemymusic.user.service.domain.ports.output.repository.common;

import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.entity.Song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResetPasswordTokenRepository {
    Optional<ResetPasswordToken> findByToken(String token);
    ResetPasswordToken save(ResetPasswordToken token);


}