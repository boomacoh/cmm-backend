package com.cuemymusic.reports.service.domain.ports.output.repository.common;





import com.cuemymusic.reports.service.domain.entity.Token;

import java.util.Optional;

public interface TokenRepository{
    Optional<Token> findByToken(String token);
}