package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.ports.output.repository.common.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogoutHandler {
    private final TokenRepository tokenRepository;

    public LogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void logout(String token) {
        var jwt = token.substring(7);

        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
