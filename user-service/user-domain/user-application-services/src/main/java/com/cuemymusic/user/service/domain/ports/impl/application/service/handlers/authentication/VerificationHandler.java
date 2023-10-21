package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication;

import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.common.ResetPasswordTokenRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerificationHandler {
    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    public VerificationHandler(
            UserRepository userRepository,
            ResetPasswordTokenRepository resetPasswordTokenRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    public void verifyAccount(String token){

    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }

}
