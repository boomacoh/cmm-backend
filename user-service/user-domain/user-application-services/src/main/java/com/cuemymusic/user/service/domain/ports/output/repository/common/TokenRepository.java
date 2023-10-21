package com.cuemymusic.user.service.domain.ports.output.repository.common;
import com.cuemymusic.user.service.domain.entity.Token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository{
    List<Token> findAllValidTokenByUser(UUID id);
    Optional<Token> findByToken(String token);
    Token save(Token token);
    List<Token> saveAll(List<Token> tokens);
}