package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.authenticate.AuthenticationCommand;
import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
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
public class AuthenticateHandler {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final AuthenticationDataMapper authenticationDataMapper;

    public AuthenticateHandler(UserRepository userRepository, TokenRepository tokenRepository, AuthenticationService authenticationService, AuthenticationDataMapper authenticationDataMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationService = authenticationService;
        this.authenticationDataMapper = authenticationDataMapper;
    }

    @Transactional
    public AuthenticationResponse login(AuthenticationCommand authenticationCommand) {
        log.warn("Starting Executing Authentication Login Endpoint");

        checkUser(authenticationCommand.getEmail());
        User given = authenticationDataMapper.authenticateUserCommandToUser(authenticationCommand);
        User user = userRepository.findByEmail(given.getEmail());
        user.setPassword(authenticationCommand.getPassword());
        log.warn("user Email: "+user.getPassword());
        authenticationService.authenticate(user.getEmail(),user.getPassword());
        revokeAllUserTokens(user);

        Token token = authenticationService.getToken(user);
        Token refeshToken = authenticationService.getRefreshToken(user);
        saveToken(token);
        return authenticationDataMapper.tokensToAuthenticationResponse(token,refeshToken);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + " doesn't exists");
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }


    private User saveUser(User user){
        User result = userRepository.save(user);
        if(result == null){
            throw new DomainException("Failed to login User with Email: "+ user.getEmail());
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

        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId().getValue());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
