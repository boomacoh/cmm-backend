package com.cuemymusic.club.service.authentication.adapter;


import com.cuemymusic.club.service.authentication.services.JwtService;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;


    @Override
    public String extractUsername(String token) {
        log.warn("ext " + token);
        return jwtService.extractUsername(token);
    }



}