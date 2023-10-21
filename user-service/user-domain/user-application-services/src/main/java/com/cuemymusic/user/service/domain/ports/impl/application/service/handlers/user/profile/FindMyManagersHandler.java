package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.mapper.users.UserManagementMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.management.UserManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FindMyManagersHandler {
    private final UserManagerRepository userManagerRepository;
    private final UserRepository userRepository;
    private final UserManagementMapper userManagementMapper;
    private final AuthenticationService authenticationService;

    public FindMyManagersHandler(UserManagerRepository userManagerRepository,
                                 UserRepository userRepository,
                                 UserManagementMapper userManagementMapper, AuthenticationService authenticationService) {
        this.userManagerRepository = userManagerRepository;
        this.userRepository = userRepository;
        this.userManagementMapper = userManagementMapper;
        this.authenticationService = authenticationService;
    }

    public FindUsersResponse find(String token){
        String userEmail = authenticationService.extractUsername(token);
        final User user = userRepository.findByEmail(userEmail);
        List<User> users = this.userRepository.findMyManagers(user.getId().getValue());
        return userManagementMapper.usersToFindUsersResponse(users);
    }

}

