package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.management;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FindClubsByUserIdHandler {
    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    public FindClubsByUserIdHandler(
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

    public ClubListResponse findClubs(String token) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        return new ClubListResponse( clubRepository.findByUser(user.getUserId().getValue()).stream().map(e->{
            e.setDevices(deviceRepository.findByUserAndClub(user.getUserId().getValue(),e.getClubId().getValue()));
            return clubDataMapper.clubToClubDTO(e);
        }).toList());
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            throw new ClubDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
