package com.cuemymusic.reports.service.authentication.adapter;

import com.cuemymusic.reports.service.authentication.services.JwtService;
import com.cuemymusic.reports.service.domain.ports.output.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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