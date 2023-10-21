package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.management;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.mapper.users.UserManagementMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.management.UserManagerRepository;
import com.cuemymusic.user.service.domain.valueobject.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FindCoachesHandler {
    private final UserManagerRepository userManagerRepository;
    private final UserRepository userRepository;
    private final UserManagementMapper userManagementMapper;
    private final AuthenticationService authenticationService;

    public FindCoachesHandler(UserManagerRepository userManagerRepository,
                              UserRepository userRepository,
                              UserManagementMapper userManagementMapper, AuthenticationService authenticationService) {
        this.userManagerRepository = userManagerRepository;
        this.userRepository = userRepository;
        this.userManagementMapper = userManagementMapper;
        this.authenticationService = authenticationService;
    }

    public FindUsersResponse find(String token, UUID club){
        String userEmail = authenticationService.extractUsername(token);
        final User user = userRepository.findByEmail(userEmail);
        List<User> users = this.userManagerRepository.findMyUsers(user.getId().getValue(), club).stream().filter(e -> e.getRole() == Role.COACH).toList();
        return userManagementMapper.usersToFindUsersResponse(users);
    }

}

