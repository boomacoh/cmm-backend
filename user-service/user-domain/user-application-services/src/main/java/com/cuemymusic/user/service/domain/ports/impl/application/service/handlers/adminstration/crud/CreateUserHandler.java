package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud;

import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserCRUDMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class CreateUserHandler {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserCRUDMapper userDataMapper;


    public CreateUserHandler(UserDomainService userDomainService, UserRepository userRepository, UserCRUDMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        checkUser(createUserCommand.email());
        UUID id = UUID.randomUUID();
        User user = userDataMapper.createUserCommandToUser(id, createUserCommand);
//        userDomainService.
        User result = saveUser(user);
        return userDataMapper.userToCreateUserResponse(result);
    }

    private void checkUser(String email) {
        if(userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

    private User saveUser(User user){
        User result = userRepository.save(user);
        if(result == null){
            throw new DomainException("Failed to Create New User with Email: "+ user.getEmail());
        }
        return result;
    }

}
