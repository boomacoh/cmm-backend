package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
import com.cuemymusic.user.service.domain.exceptions.AuthException;
import com.cuemymusic.user.service.domain.mapper.authentication.AuthenticationDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.TokenRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RefreshTokenHandler {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final AuthenticationDataMapper authenticationDataMapper;

    public RefreshTokenHandler(UserDomainService userDomainService,
                               UserRepository userRepository,
                               TokenRepository tokenRepository,
                               AuthenticationService authenticationService,
                               AuthenticationDataMapper authenticationDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationService = authenticationService;
        this.authenticationDataMapper = authenticationDataMapper;
    }

    @Transactional
    public AuthenticationResponse refreshToken(String authHeader) throws DomainException{
        try{
            String userEmail = authenticationService.extractUsername(authHeader);
            checkUser(userEmail);
            User user = userRepository.findByEmail(userEmail);
            if (authenticationService.isTokenValid(authHeader, user)) {
                Token token = authenticationService.getToken(user);
                revokeAllUserTokens(user);
                Token refeshToken = authenticationService.getRefreshToken(user);
                log.warn("Will Commit Password :" + user.getPassword());
                saveUser(user);
                saveToken(token);
                return authenticationDataMapper.tokensToAuthenticationResponse(token,refeshToken);
            }
        }
        finally {
            throw new AuthException("Failed to open token",9001);
        }
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }
    private User saveUser(User user){
        User result = userRepository.save(user);
        if(result == null){
            throw new DomainException("Failed to Create New User with Email: "+ user.getEmail());
        }
        return result;
    }
    private Token saveToken(Token token){
        Token result = tokenRepository.save(token);
        if(result == null){
            throw new DomainException("Failed to get New Token with for user with Email: "+ token.getUser().getEmail());
        }
        return result;
    }

    private void revokeAllUserTokens(User user) {
        user.getTokens().forEach(token->{
            token.setExpired(true);
            token.setRevoked(true);
        });
        userRepository.save(user);
    }
}
