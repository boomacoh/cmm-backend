package com.cuemymusic.user.service.authentication.services;

import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import com.cuemymusic.user.service.domain.ports.input.service.UserLogoutApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final UserLogoutApplicationService userLogoutApplicationService;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        token = authHeader;
        userLogoutApplicationService.logout(token);
        SecurityContextHolder.clearContext();

    }
}