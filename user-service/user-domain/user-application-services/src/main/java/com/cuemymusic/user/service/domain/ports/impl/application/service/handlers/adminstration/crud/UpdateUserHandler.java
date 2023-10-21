package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud;

import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserCRUDMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class UpdateUserHandler {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserCRUDMapper userDataMapper;


    public UpdateUserHandler(UserDomainService userDomainService, UserRepository userRepository, UserCRUDMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        User user = userDataMapper.updateUserCommandToUser(updateUserCommand);
        checkUser(user.getId().getValue());
        User originalUser = userRepository.findById(user.getId().getValue());
        User result = updateUser(user,originalUser);
        return userDataMapper.userToUpdateUserResponse(result);
    }

    private void checkUser(UUID email) {
        if(!userRepository.isPresentById(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

    private User saveUser(User user){
        User result = userRepository.save(user);
        if(result == null){
            throw new DomainException("Failed to Update User ID : "+ user.getId().getValue());
        }
        return result;
    }

    private User updateUser(User user, User originalUser){
        originalUser.setFirstName(user.getFirstName());
        originalUser.setLastName(user.getLastName());
        originalUser.setSubscription(user.getSubscription());
//        originalUser.setRole(user.getRole());
        User result = saveUser(originalUser);
        return result;
    }

}
