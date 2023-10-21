package com.cuemymusic.user.service.dataaccess.token.adapter;

import com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity;
import com.cuemymusic.user.service.dataaccess.token.mapper.TokenDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.token.repository.TokenJpaRepository;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.ports.output.repository.common.TokenRepository;
import com.cuemymusic.user.service.domain.entity.Token;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenRepositoryImpl implements TokenRepository {
    private final TokenJpaRepository tokenJpaRepository;
    private final TokenDataAccessMapper tokenDataAccessMapper;
    private final UserDataAccessMapper userDataAccessMapper;

    public TokenRepositoryImpl(TokenJpaRepository tokenJpaRepository,
                               TokenDataAccessMapper tokenDataAccessMapper,
                               UserDataAccessMapper userDataAccessMapper) {
        this.tokenJpaRepository = tokenJpaRepository;
        this.tokenDataAccessMapper = tokenDataAccessMapper;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Override
    public List<Token> findAllValidTokenByUser(UUID id) {
        return tokenJpaRepository.findAllValidTokenByUser(id).stream().map(tokenDataAccessMapper::tokenEntityToToken).toList();
    }



    @Override
    public Optional<Token> findByToken(String token) {
        return tokenJpaRepository.findByToken(token).map(tokenDataAccessMapper::tokenEntityToToken);
    }

    @Override
    public Token save(Token token) {
        return tokenDataAccessMapper.tokenEntityToToken(tokenJpaRepository.save(tokenDataAccessMapper.tokenToTokenEntity(token)));
    }

    @Override
    public List<Token> saveAll(List<Token> tokens) {
        List<Token> result =  tokenJpaRepository
                .saveAll(
                        tokens.stream()
                                .map(tokenDataAccessMapper::tokenToTokenEntity)
                                .toList()
                ).stream().
                map(tokenDataAccessMapper::tokenEntityToToken)
                .toList();
        return result;
    }

}
