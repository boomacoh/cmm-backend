package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile;

import com.cuemymusic.user.service.domain.dto.user.profile.UpdateUserProfileCommand;
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserProfileMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class UpdateProfileInfoHandler {
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;
    private final AuthenticationService authenticationService;

    public UpdateProfileInfoHandler(
            UserRepository userRepository,
             UserProfileMapper userProfileMapper,
             AuthenticationService authenticationService
    ) {
        this.userRepository = userRepository;
        this.userProfileMapper = userProfileMapper;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public UserProfileResponse updateInfo(String authHeader, UpdateUserProfileCommand updateUserProfileCommand){
        String userEmail = authenticationService.extractUsername(authHeader);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        User update = userProfileMapper.updateUserProfileCommandToUser(updateUserProfileCommand,user);
        user.setFirstName(update.getFirstName());
        user.setLastName(update.getLastName());
        user.setDevices(update.getDevices());
        final User result = userRepository.save(user);
        return userProfileMapper.userToUserProfileResponse(result);
    }

    @Transactional
    public void removeDevice(String authHeader, Integer rank){
        String userEmail = authenticationService.extractUsername(authHeader);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        userRepository.removeDevice(user.getId().getValue(),rank);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new UserDomainException("User with EMAIL: " + email + " doesn't exists");
        }
    }

}
