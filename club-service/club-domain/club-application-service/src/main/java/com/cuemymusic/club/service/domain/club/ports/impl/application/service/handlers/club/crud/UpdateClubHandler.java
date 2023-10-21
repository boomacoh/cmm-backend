package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud;

import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
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
public class UpdateClubHandler {

    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final UserRepository userRepository;

    public UpdateClubHandler(ClubRepository clubRepository, ClubDataMapper clubDataMapper, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    public UpdateClubResponse updateClub(UpdateClubCommand updateClubCommand) {
        checkUser(updateClubCommand.adminEmail());
        User admin = userRepository.findByEmail(updateClubCommand.adminEmail());
        UUID clubId = updateClubCommand.clubId();
        if(!clubRepository.isPresentById(clubId)){
            throw new ClubDomainException("Club not found");
        }
        Club originalClub = clubRepository.findById(clubId);
        Club club = clubDataMapper.updateClubCommandToClub(updateClubCommand,admin.getUserId());
        Club result = updateClub(club,originalClub);
        return clubDataMapper.clubToUpdateClubResponse(result);
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + " doesn't exists");
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
    private Club updateClub(Club club, Club originalClub){
        originalClub.setAdminId(club.getAdminId());
        originalClub.setCountry(club.getCountry());
        originalClub.setName(club.getName());
        originalClub.setEmail(club.getEmail());
        originalClub.setCityName(club.getCityName());
        originalClub.setPhoneNumber(club.getPhoneNumber());
        originalClub.setZipCode(club.getZipCode());
        originalClub.setStreetAddress(club.getStreetAddress());
        Club result = saveClub(originalClub);
        return result;
    }

}
