package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.by.id.FindUserByIdResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserCRUDMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FindUserByIdHandler {
    private final UserRepository userRepository;
    private final UserCRUDMapper userDataMapper;

    public FindUserByIdHandler(
            UserRepository userRepository,
            UserCRUDMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    public FindUserByIdResponse find(UUID id) {
        checkUser(id);
        User user=  userRepository.findById(id);
        return new FindUserByIdResponse(userDataMapper.userToUserDTO(user));
    }

    private void checkUser(UUID email) {
        if(!userRepository.isPresentById(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
