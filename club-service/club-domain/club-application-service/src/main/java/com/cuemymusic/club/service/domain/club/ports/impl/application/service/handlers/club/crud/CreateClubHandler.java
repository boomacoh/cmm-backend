package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;

import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;

import com.cuemymusic.user.service.domain.valueobject.ClubId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class CreateClubHandler {


    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final UserRepository userRepository;

    public CreateClubHandler(ClubRepository clubRepository, ClubDataMapper clubDataMapper, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    public CreateClubResponse createClub(CreateClubCommand createClubCommand) {
        checkUser(createClubCommand.adminEmail());
        User admin = userRepository.findByEmail(createClubCommand.adminEmail());
        UUID id = UUID.randomUUID();
        Club club = clubDataMapper.addClubCommandToClub(createClubCommand,admin.getUserId());
        club.setClubId(new ClubId(id));
        club.setDevices(List.of());
        club.setDisabledUsers(List.of());
        Club result = saveClub(club);
        return clubDataMapper.clubToCreateClubResponse(result);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "doesn't exists");
            throw new ClubDomainException("User with EMAIL: " + email + "already exists");
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
