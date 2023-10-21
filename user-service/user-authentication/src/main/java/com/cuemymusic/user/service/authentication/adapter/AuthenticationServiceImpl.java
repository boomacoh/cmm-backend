package com.cuemymusic.user.service.authentication.adapter;
import com.cuemymusic.user.service.authentication.entities.AuthUser;
import com.cuemymusic.user.service.authentication.mapper.AuthenticationMapper;
import com.cuemymusic.user.service.authentication.services.JwtService;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.authentication.services.MailService;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import com.cuemymusic.user.service.domain.valueobject.TokenType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final AuthenticationMapper authenticationMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userRepository;


    @Override
    public String hashPassword(String password) {

        return passwordEncoder.encode(password);
    }

    @Override
    public Token getRefreshToken(User user) {
        AuthUser authUser =  authenticationMapper.domainUserToAuthUser(
                user,
                user.getTokens()
                        .stream()
                        .map( e -> authenticationMapper.domainTokenToAuthToken(e))
                        .toList()
        );
        authUser.getTokens()
                .stream()
                .forEach(
                        e -> e.setUser(authUser)
                );
        return new Token(
                null,
                jwtService.generateRefreshToken(authUser),
                TokenType.BEARER,
                false,
                false,
                user
        );

    }

    @Override
    public Token getToken(User user) {
        AuthUser authUser =  authenticationMapper.domainUserToAuthUser(
                user,
                user.getTokens()
                        .stream()
                        .map( e -> authenticationMapper.domainTokenToAuthToken(e))
                        .toList()
        );
        authUser.getTokens()
                .stream()
                .forEach(
                        e -> e.setUser(authUser)
                );
        return new Token(
                new TokenId(UUID.randomUUID()),
                jwtService.generateToken(authUser),
                TokenType.BEARER,
                false,
                false,
                user
        );
    }

    @Override
    public void authenticate(String username,String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

    }

    @Override
    public String extractUsername(String token) {
        log.warn("ext " + token);
        return jwtService.extractUsername(token);
    }

    @Override
    public boolean isTokenValid(String refreshToken, User user) {
        AuthUser authUser =  authenticationMapper.domainUserToAuthUser(
                user,
                user.getTokens()
                        .stream()
                        .map( e -> authenticationMapper.domainTokenToAuthToken(e))
                        .toList()
        );
        authUser.getTokens()
                .stream()
                .forEach(
                        e -> e.setUser(authUser)
                );
        return jwtService.isTokenValid(
                refreshToken,
                authUser
        );
    }

    @Override
    public void sendResetPasswordEmail(String token, User user) {
        mailService.sendResetPasswordMail(token,user);
    }


}