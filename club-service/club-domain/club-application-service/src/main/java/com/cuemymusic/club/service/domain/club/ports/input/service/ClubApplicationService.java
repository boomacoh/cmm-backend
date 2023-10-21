package com.cuemymusic.club.service.domain.club.ports.input.service;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.management.*;
import jakarta.validation.Valid;

import java.util.UUID;

public interface ClubApplicationService {
    ////////////////////////////////////
    // CRUD Admin endpoints ############
    ////////////////////////////////////
    CreateClubResponse createClub(@Valid CreateClubCommand createClubCommand);
    ClubListResponse findAll();
    ClubDTO findById(@Valid UUID id);
    UpdateClubResponse updateClub(@Valid UpdateClubCommand updateClubCommand);
    DeleteClubResponse deleteClub(@Valid UUID id);
    ////////////////////////////////////
    // Club Manager endpoints ##########
    ////////////////////////////////////
    void disableUser(String token, UUID clubId, UUID userId);
    void enableUser(String token, UUID clubId, UUID userId);
    void enableAll(String token, UUID clubId);
    ManageClubResponse manageClub(String token, UUID id, ManageClubCommand manageClubCommand);
    ClubListResponse findManagedByUser(String token);
    UsersListResponse findDisabledUsers(UUID clubId);

    ////////////////////////////////////
    // USER endpoints ##################
    ////////////////////////////////////
    ClubListResponse findByUser(String token);
}
