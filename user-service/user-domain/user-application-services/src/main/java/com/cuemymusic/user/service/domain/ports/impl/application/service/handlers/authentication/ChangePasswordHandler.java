package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.password.change.ChangePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordResponse;
import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.ResetPasswordTokenRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ChangePasswordHandler {
    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    private final AuthenticationService authenticationService;

    public ChangePasswordHandler(
            UserRepository userRepository,
            ResetPasswordTokenRepository resetPasswordTokenRepository,
            AuthenticationService authenticationService
    ) {
        this.userRepository = userRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.authenticationService = authenticationService;
    }

    public void update(String token, ChangePasswordCommand changePasswordCommand){
        String newPassword = authenticationService.hashPassword(changePasswordCommand.newPassword());
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        final User user = userRepository.findByEmail(userEmail);
        try {
            authenticationService.authenticate(user.getEmail(), changePasswordCommand.currentPassword());
            user.setPassword(newPassword);
            userRepository.save(user);
        }catch (Exception e){
            throw new UserDomainException("wrong current password");
        }

    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }
}
