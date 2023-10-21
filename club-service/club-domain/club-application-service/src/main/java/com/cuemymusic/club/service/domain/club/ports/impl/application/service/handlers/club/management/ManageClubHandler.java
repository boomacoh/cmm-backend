package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.management;

import com.cuemymusic.club.service.domain.club.dto.club.management.DisableUserCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.EnableUserCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.ManageClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.ManageClubResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class ManageClubHandler {

    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public ManageClubHandler(ClubRepository clubRepository,
                             ClubDataMapper clubDataMapper,
                             UserRepository userRepository,
                             AuthenticationService authenticationService) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }


    @Transactional
    public ManageClubResponse manageClub(String token, UUID id, ManageClubCommand manageClubCommand) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        checkClub(id);
        User admin = userRepository.findByEmail(userEmail);
        Club club = clubRepository.findById(id);
        if(club.getAdminId().getValue().equals( admin.getUserId().getValue())){
            club.setAnnounce(manageClubCommand.announce());
            club.setMaxCoachPumps(manageClubCommand.maxCoachPumps());
            club.setMaxUserSongs(manageClubCommand.maxUserSongs());
            club.setMaxCoachSongs(manageClubCommand.maxCoachSongs());
            saveClub(club);
            Club newClub = clubRepository.findById(id);
            return ManageClubResponse.builder()
                    .announce(newClub.getAnnounce())
                    .maxCoachPump(newClub.getMaxCoachPumps())
                    .maxUserSongs(newClub.getMaxUserSongs())
                    .maxCoachSongs(newClub.getMaxCoachSongs())
                    .build();
        }else{
            throw new ClubDomainException("You are not authorized");
        }
    }


    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + " Doesn't exists");
            throw new ClubDomainException("User with EMAIL: " + email + " Doesn't exists");
        }
    }

    private void checkClub(UUID id) {
        if(!clubRepository.isPresentById(id)){
            log.warn("Club with Id: " + id + " Doesn't exist");
            throw new ClubDomainException("User with Id: " + id + " Doesn't exists");
        }
    }
    private Club saveClub(Club club){
        Club result = clubRepository.save(club);
        if(result == null){
            throw new DomainException("Failed to Create New Club with name: "+ club.getName());
        }
        return result;
    }


}
