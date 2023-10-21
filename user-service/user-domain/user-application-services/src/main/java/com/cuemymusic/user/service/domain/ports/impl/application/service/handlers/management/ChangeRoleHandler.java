package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.management;

import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleCommand;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserManagementMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.management.UserManagerRepository;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class ChangeRoleHandler {
    private final UserRepository userRepository;
    private final UserManagerRepository userManagerRepository;
    private final UserManagementMapper userRolesMapper;
    private final AuthenticationService authenticationService;

    public ChangeRoleHandler(UserRepository userRepository,
                             UserManagerRepository userManagerRepository,
                             UserManagementMapper userRolesMapper,
                             AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.userManagerRepository = userManagerRepository;
        this.userRolesMapper = userRolesMapper;
        this.authenticationService = authenticationService;
    }


    @Transactional
    public ChangeRoleResponse addCoach(String token,UUID clubId, UUID userId) {
        String userEmail = authenticationService.extractUsername(token);
        final User admin = userRepository.findByEmail(userEmail);
        checkUser(admin.getId().getValue(),userId,clubId);
        User user = userManagerRepository.findMyUserById(admin.getId().getValue(),userId,clubId);
        Role userRole = Role.COACH;
        user.setRole(userRole);
        userManagerRepository.assignCoach(clubId,userId);
        userManagerRepository.save(user);
        return userRolesMapper.userToChangeRoleResponse(user);
    }

    @Transactional
    public ChangeRoleResponse removeCoach(String token,UUID clubId, UUID userId) {
        String userEmail = authenticationService.extractUsername(token);
        final User admin = userRepository.findByEmail(userEmail);
        checkUser(admin.getId().getValue(),userId,clubId);
        User user = userManagerRepository.findMyUserById(admin.getId().getValue(),userId,clubId);
        Role userRole = Role.USER;
        user.setRole(userRole);
        userManagerRepository.revokeCoach(clubId,userId);
        userManagerRepository.save(user);
        return userRolesMapper.userToChangeRoleResponse(user);
    }
    @Transactional
    public void removeAllCoach(String token,UUID clubId) {
        String userEmail = authenticationService.extractUsername(token);
        final User admin = userRepository.findByEmail(userEmail);
        userManagerRepository.revokeAllCoaches(admin.getId().getValue(),clubId);
    }
    private void checkUser(UUID adminId,UUID id,UUID clubId) {
        if(!userManagerRepository.isMyUserPresent(adminId,id,clubId)){
            log.warn("User with ID: " + id + " not found");
            throw new UserDomainException("User with ID: " + id + " not found");
        }
    }
}
