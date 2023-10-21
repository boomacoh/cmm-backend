package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile;

import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserProfileMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;

@Component
public class GetProfileInfoHandler {
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;
    private final AuthenticationService authenticationService;

    public GetProfileInfoHandler(
            UserRepository userRepository,
             UserProfileMapper userProfileMapper,
             AuthenticationService authenticationService
    ) {
        this.userRepository = userRepository;
        this.userProfileMapper = userProfileMapper;
        this.authenticationService = authenticationService;
    }

    public UserProfileResponse getInfo(String authHeader){
        String userEmail = authenticationService.extractUsername(authHeader);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        return userProfileMapper.userToUserProfileResponse(user);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }

}
