package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.management;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.management.UsersListResponse;
import com.cuemymusic.club.service.domain.club.dto.common.UserDTO;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FindDisabledUsersHandler {
    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;
    public FindDisabledUsersHandler(
            ClubRepository clubRepository,
            ClubDataMapper clubDataMapper,
            DeviceRepository deviceRepository, UserRepository userRepository,
            AuthenticationService authenticationService) {

        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public UsersListResponse findDisabledUsers(UUID clubId) {
        if(!clubRepository.isPresentById(clubId)){
            throw new ClubDomainException("Club with Id: [" + clubId + "] doesn't exist exists");
        }
        return new UsersListResponse( clubRepository.findById(clubId).getDisabledUsers().stream().map(e->
                 UserDTO.builder()
                        .firstName(e.getFirstName())
                        .lastName(e.getLastName())
                        .id(e.getUserId().getValue())
                        .email(e.getEmail())
                        .build()
            ).toList()
        );
    }


}
