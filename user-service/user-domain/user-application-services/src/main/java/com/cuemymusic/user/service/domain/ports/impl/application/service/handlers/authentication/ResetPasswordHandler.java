package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordResponse;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.ResetPasswordTokenRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.event.ResetPasswordTokenCreatedEvent;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResetPasswordHandler {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final UserDomainService userDomainService;

    public ResetPasswordHandler(
            UserRepository userRepository,
            AuthenticationService authenticationService,
            ResetPasswordTokenRepository resetPasswordTokenRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;

        this.authenticationService = authenticationService;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.userDomainService = userDomainService;
    }

    public ResetPasswordResponse resetPassword(ResetPasswordCommand resetPasswordCommand){
        checkUser(resetPasswordCommand.email());
        User user = userRepository.findByEmail(resetPasswordCommand.email());
        log.warn("USER ID " + user.getId());

        final ResetPasswordTokenCreatedEvent resetPasswordTokenCreatedEvent = userDomainService.createPasswordResetTokenForUser(user);
        final var test = resetPasswordTokenRepository.save(resetPasswordTokenCreatedEvent.token());
        log.warn("USER ID " + test.getUser().getId().getValue().toString());

        authenticationService.sendResetPasswordEmail(
                resetPasswordTokenCreatedEvent.token().getToken(),
                user
        );

        return new ResetPasswordResponse(true);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }

}
