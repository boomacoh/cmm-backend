package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud;

import com.cuemymusic.user.service.domain.dto.user.adminstration.delete.DeleteUserResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserCRUDMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class DeleteUserHandler {
    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final UserCRUDMapper userDataMapper;


    public DeleteUserHandler(UserDomainService userDomainService, UserRepository userRepository, UserCRUDMapper userDataMapper) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    @Transactional
    public DeleteUserResponse deleteUser(UUID id) {
        checkUser(id);
        return userDataMapper.booleanToDeleteUserResponse(
                delete(id)
        );
    }

    private void checkUser(UUID email) {
        if(!userRepository.isPresentById(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

    private Boolean delete(UUID userId) throws DomainException{
        try {
            userRepository.deleteById(userId);
            log.info("User with ID: " + userId + " deleted.");
            return true;
        }catch (Exception e){
            throw new DomainException("Failed to Delete User ID: "+ userId);
        }
    }

}
