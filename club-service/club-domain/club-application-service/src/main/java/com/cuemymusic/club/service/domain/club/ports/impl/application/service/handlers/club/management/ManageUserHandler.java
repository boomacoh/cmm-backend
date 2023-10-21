package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.management;

import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.DisableUserCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.EnableUserCommand;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.authentication.AuthenticationService;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import com.cuemymusic.user.service.domain.valueobject.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ManageUserHandler {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public ManageUserHandler(ClubRepository clubRepository,
                             UserRepository userRepository,
                             AuthenticationService authenticationService) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }


    @Transactional
    public void disableUser(String token, UUID clubId, UUID userId) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        checkUser(userId);
        checkClub(clubId);
        User admin = userRepository.findByEmail(userEmail);
        Club club = clubRepository.findById(clubId);
        User user = userRepository.findById(userId);
        if(club.getAdminId().getValue().equals(admin.getUserId().getValue())){

            club.addDisabled(user);
            saveClub(club);
        }else{
            throw new ClubDomainException("You are not authorized");
        }
    }
    @Transactional
    public void enableUser(String token, UUID clubId, UUID userId) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        checkUser(userId);
        checkClub(clubId);
        User admin = userRepository.findByEmail(userEmail);
        Club club = clubRepository.findById(clubId);
        User user = userRepository.findById(userId);
        if(club.getAdminId().getValue() .equals( admin.getUserId().getValue())){
            club.removeDisabled(user);
            saveClub(club);
        }else{
            throw new ClubDomainException("You are not authorized");
        }
    }
    @Transactional
    public void enableAll(String token, UUID clubId) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        checkClub(clubId);
        User admin = userRepository.findByEmail(userEmail);
        Club club = clubRepository.findById(clubId);
        if(club.getAdminId().getValue() .equals( admin.getUserId().getValue())){
            club.setDisabledUsers(List.of());
            saveClub(club);
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
    private void checkUser(UUID id) {
        if(!userRepository.isPresentById(id)){
            log.warn("User with Id: " + id + " Doesn't exist");
            throw new ClubDomainException("User with Id: " + id + " Doesn't exists");
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
