package com.cuemymusic.club.service.domain.club.ports.impl;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.management.ManageClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.management.ManageClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.management.UsersListResponse;
import com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud.*;
import com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.management.*;
import com.cuemymusic.club.service.domain.club.ports.input.service.ClubApplicationService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClubApplicationServiceImpl implements ClubApplicationService {
    //////////////////////////////
    // Club CRUD HANDLERS
    /////////////////////////////
    final private FindClubsHandler findClubsHandler;
    final private FindClubByIdHandler findClubByIdHandler;
    final private CreateClubHandler createClubHandler;
    final private DeleteClubHandler deleteClubHandler;
    final private UpdateClubHandler updateClubHandler;

    //////////////////////////////
    // Club Management HANDLERS
    /////////////////////////////
    final private ManageUserHandler manageUserHandler;
    final private ManageClubHandler manageClubHandler;
    final private FindClubsByUserIdHandler findClubsByUserIdHandler;
    final private FindManagedClubsByUserIdHandler findManagedClubsByUserIdHandler;
    final private FindDisabledUsersHandler findDisabledUsersHandler;
    public ClubApplicationServiceImpl(FindClubsHandler findClubsHandler,
                                      FindClubByIdHandler findClubByIdHandler,
                                      CreateClubHandler createClubHandler,
                                      DeleteClubHandler deleteClubHandler,
                                      UpdateClubHandler updateClubHandler,
                                      ManageUserHandler manageUserHandler,
                                      ManageClubHandler manageClubHandler,
                                      FindClubsByUserIdHandler findClubsByUserIdHandler,
                                      FindManagedClubsByUserIdHandler findManagedClubsByUserIdHandler,
                                      FindDisabledUsersHandler findDisabledUsersHandler) {
        this.findClubsHandler = findClubsHandler;
        this.findClubByIdHandler = findClubByIdHandler;
        this.createClubHandler = createClubHandler;
        this.deleteClubHandler = deleteClubHandler;
        this.updateClubHandler = updateClubHandler;
        this.manageUserHandler = manageUserHandler;
        this.manageClubHandler = manageClubHandler;
        this.findClubsByUserIdHandler = findClubsByUserIdHandler;
        this.findManagedClubsByUserIdHandler = findManagedClubsByUserIdHandler;
        this.findDisabledUsersHandler = findDisabledUsersHandler;
    }

    @Override
    public CreateClubResponse createClub(CreateClubCommand createClubCommand) {
        return createClubHandler.createClub(createClubCommand);
    }

    @Override
    public ClubListResponse findAll() {
        return findClubsHandler.findAll();
    }

    @Override
    public ClubDTO findById(UUID id) {
        return findClubByIdHandler.find(id);
    }

    @Override
    public UpdateClubResponse updateClub(UpdateClubCommand updateClubCommand) {
        return updateClubHandler.updateClub(updateClubCommand);
    }

    @Override
    public DeleteClubResponse deleteClub(UUID id) {
        return deleteClubHandler.deleteClub(id);
    }

    @Override
    public void disableUser(String token, UUID clubId, UUID userId) {
        manageUserHandler.disableUser(token, clubId, userId);
    }

    @Override
    public void enableUser(String token, UUID clubId, UUID userId) {
        manageUserHandler.enableUser(token, clubId, userId);
    }

    @Override
    public void enableAll(String token, UUID clubId) {
        manageUserHandler.enableAll(token, clubId);
    }

    @Override
    public ManageClubResponse manageClub(String token, UUID id, ManageClubCommand manageClubCommand) {
        return manageClubHandler.manageClub(token, id, manageClubCommand);
    }

    @Override
    public ClubListResponse findByUser(String token) {
        return findClubsByUserIdHandler.findClubs(token);
    }

    @Override
    public ClubListResponse findManagedByUser(String token) {
        return findManagedClubsByUserIdHandler.findClubs(token);
    }

    @Override
    public UsersListResponse findDisabledUsers(UUID clubId) {
        return findDisabledUsersHandler.findDisabledUsers(clubId);
    }




}
