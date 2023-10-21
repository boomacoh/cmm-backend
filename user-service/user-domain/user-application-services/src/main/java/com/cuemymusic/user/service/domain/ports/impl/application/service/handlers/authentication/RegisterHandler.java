package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
import com.cuemymusic.user.service.domain.dto.authentication.register.RegisterCommand;
import com.cuemymusic.user.service.domain.ports.output.repository.common.TokenRepository;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.mapper.authentication.AuthenticationDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.event.UserRegisteredEvent;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RegisterHandler {
    private final  UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final AuthenticationDataMapper authenticationDataMapper;

    public RegisterHandler(UserDomainService userDomainService, UserRepository userRepository, TokenRepository tokenRepository, AuthenticationService authenticationService, AuthenticationDataMapper authenticationDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationService = authenticationService;
        this.authenticationDataMapper = authenticationDataMapper;
    }

    @Transactional
    public AuthenticationResponse register(RegisterCommand registerCommand) {
        checkUser(registerCommand.email());
        String password = authenticationService.hashPassword(registerCommand.password());
        UserRegisteredEvent userRegisteredEvent = userDomainService.register(
                authenticationDataMapper.registerUserCommandToUser(registerCommand),
                password
        );
        User user = userRegisteredEvent.user();
        Token token = authenticationService.getToken(user);
        Token refeshToken = authenticationService.getRefreshToken(user);
        log.warn(user.getDevices().toString());
        saveUser(user);
        saveToken(token);
        return authenticationDataMapper.tokensToAuthenticationResponse(token,refeshToken);
    }

    private void checkUser(String email) {
        if(userRepository.isPresentByEmail(email)){
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

}
