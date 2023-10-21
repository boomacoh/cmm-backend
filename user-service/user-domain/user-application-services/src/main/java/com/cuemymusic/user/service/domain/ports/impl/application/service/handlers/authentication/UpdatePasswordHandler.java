package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordResponse;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.ResetPasswordTokenRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UpdatePasswordHandler {
    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    private final AuthenticationService authenticationService;

    public UpdatePasswordHandler(
            UserRepository userRepository,

            ResetPasswordTokenRepository resetPasswordTokenRepository, AuthenticationService authenticationService
    ) {
        this.userRepository = userRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;

        this.authenticationService = authenticationService;
    }

    public UpdatePasswordResponse update(String token, UpdatePasswordCommand updatePasswordCommand){

        final Optional<ResetPasswordToken> passToken = resetPasswordTokenRepository.findByToken(token);

        if(passToken.isPresent()){
            ResetPasswordToken resetPasswordToken = passToken.get();
            if(resetPasswordToken.getRevoked()){
                throw new UserDomainException("INVALID LINK");
            }
            resetPasswordToken.setRevoked(true);
            resetPasswordTokenRepository.save(resetPasswordToken);
            if(!isTokenExpired(resetPasswordToken)){
                User user = resetPasswordToken.getUser();
                String password = authenticationService.hashPassword(updatePasswordCommand.password());
                user.setPassword(password);
                user.setTokens(List.of());
                userRepository.save(user);
                return new UpdatePasswordResponse(true);
            }
        }

        return new UpdatePasswordResponse(false);
    }

    private boolean isTokenExpired(ResetPasswordToken passToken) {
        return passToken.getExpiryDate().isBefore(ZonedDateTime.now());
    }
}
