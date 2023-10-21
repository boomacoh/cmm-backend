package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.mapper.users.UserCRUDMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FindUsersHandler {
    private final UserRepository userRepository;
    private final UserCRUDMapper userDataMapper;

    public FindUsersHandler(
            UserRepository userRepository,
            UserCRUDMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    public FindUsersResponse findAll() {
        return new FindUsersResponse( userRepository.findAll().stream().map(e->userDataMapper.userToUserDTO(e)).toList());
    }


}
