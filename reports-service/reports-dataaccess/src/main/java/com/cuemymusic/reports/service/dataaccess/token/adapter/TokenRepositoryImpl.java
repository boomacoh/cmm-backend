package com.cuemymusic.reports.service.dataaccess.token.adapter;


import com.cuemymusic.reports.service.dataaccess.token.mapper.TokenDataAccessMapper;
import com.cuemymusic.reports.service.dataaccess.token.repository.TokenJpaRepository;
import com.cuemymusic.reports.service.domain.entity.Token;
import com.cuemymusic.reports.service.domain.ports.output.repository.common.TokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenRepositoryImpl implements TokenRepository {
    private final TokenJpaRepository tokenJpaRepository;
    private final TokenDataAccessMapper tokenDataAccessMapper;

    public TokenRepositoryImpl(TokenJpaRepository tokenJpaRepository,
                               TokenDataAccessMapper tokenDataAccessMapper) {
        this.tokenJpaRepository = tokenJpaRepository;
        this.tokenDataAccessMapper = tokenDataAccessMapper;
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenJpaRepository.findByToken(token).map(tokenDataAccessMapper::tokenEntityToToken);
    }

}
