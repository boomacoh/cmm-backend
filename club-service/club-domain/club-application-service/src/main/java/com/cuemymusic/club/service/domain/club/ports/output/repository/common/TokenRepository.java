package com.cuemymusic.club.service.domain.club.ports.output.repository.common;



import com.cuemymusic.club.service.domain.entity.Token;

import java.util.Optional;

public interface TokenRepository{
    Optional<Token> findByToken(String token);
}