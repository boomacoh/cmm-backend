package com.cuemymusic.firmware.service.domain.ports.output.repository.common;




import com.cuemymusic.firmware.service.domain.entity.Token;

import java.util.Optional;

public interface TokenRepository{
    Optional<Token> findByToken(String token);
}