package com.cuemymusic.user.service.application.rest;

import com.cuemymusic.user.service.domain.dto.authentication.authenticate.AuthenticationCommand;
import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordResponse;
import com.cuemymusic.user.service.domain.dto.authentication.register.RegisterCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordResponse;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth", produces = "application/vnd.api.v1+json")
@AllArgsConstructor
public class AuthenticationController {
    private final UserApplicationService userApplicationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterCommand registerCommand){
        log.info("Creating User with email : {} ",registerCommand.email());
        AuthenticationResponse authenticationResponse = userApplicationService.register(registerCommand);
        return ResponseEntity.ok(authenticationResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationCommand authenticationCommand){
        log.info("Logging in User with email : {} ",authenticationCommand.getEmail());
        AuthenticationResponse authenticationResponse = userApplicationService.authenticate(authenticationCommand);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordCommand resetPasswordCommand){
        log.info("Logging in User with email : {} ",resetPasswordCommand.email());
        ResetPasswordResponse authenticationResponse = userApplicationService.resetPassword(resetPasswordCommand);
        return ResponseEntity.ok(authenticationResponse);
    }
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<UpdatePasswordResponse> resetPassword(@PathVariable String token, @RequestBody UpdatePasswordCommand updatePasswordCommand){
        UpdatePasswordResponse updatePasswordResponse = userApplicationService.updatePassword(token,updatePasswordCommand);
        return ResponseEntity.ok(updatePasswordResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) throws IOException {
        final String refreshToken;
        log.warn("Refreshing from controller : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new UserDomainException("Unauthorized Refresh Token");
        }
        refreshToken = authHeader.substring(7);
        log.warn("Refreshing from controller : "+refreshToken);
        AuthenticationResponse authenticationResponse = userApplicationService.refreshToken(refreshToken);
        return  ResponseEntity.ok(authenticationResponse);
    }

}
