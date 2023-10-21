package com.cuemymusic.user.service.domain.ports.impl;

import com.cuemymusic.user.service.domain.dto.authentication.authenticate.AuthenticationCommand;
import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
import com.cuemymusic.user.service.domain.dto.authentication.password.change.ChangePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.update.UpdatePasswordResponse;
import com.cuemymusic.user.service.domain.dto.authentication.register.RegisterCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.password.reset.ResetPasswordResponse;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongResponse;
import com.cuemymusic.user.service.domain.dto.song.update.UpdateName;
import com.cuemymusic.user.service.domain.dto.user.favorite.clear.ClearFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.user.favorite.set.SetFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.song.add.AddSongResponse;
import com.cuemymusic.user.service.domain.dto.song.remove.RemoveSongResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.delete.DeleteUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.by.id.FindUserByIdResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserResponse;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.dto.user.profile.UpdateUserProfileCommand;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.adminstration.crud.*;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.management.ChangeRoleHandler;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication.*;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.management.FindCoachesHandler;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.management.FindMyUsersHandler;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile.FindMyManagersHandler;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs.*;
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile.GetProfileInfoHandler;
import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.profile.UpdateProfileInfoHandler;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {
    //////////////////////////////
    // CRUD HANDLERS
    /////////////////////////////
    final private FindUsersHandler findUsersHandler;
    final private FindUserByIdHandler findUserByIdHandler;
    final private CreateUserHandler createUserHandler;
    final private DeleteUserHandler deleteUserHandler;
    final private UpdateUserHandler updateUserHandler;

    ////////////////////////////////////////
    // Authentication Handlers
    ////////////////////////////////////////
    final private RegisterHandler registerHandler;
    final private RefreshTokenHandler refreshTokenHandler;
    final private AuthenticateHandler authenticateHandler;
    final private ResetPasswordHandler resetPasswordHandler;
    final private UpdatePasswordHandler updatePasswordHandler;
    final private ChangePasswordHandler changePasswordHandler;

    //////////////////////////////////////////
    // User Management Handlers
    //////////////////////////////////////////
    final private ChangeRoleHandler changeRoleHandler;
    final private FindMyUsersHandler findMyUsersHandler;
    final private FindCoachesHandler findCoachesHandler;

    //////////////////////////////////////////
    // User Profile Handlers
    //////////////////////////////////////////
    final private GetProfileInfoHandler getProfileInfoHandler;
    final private UpdateProfileInfoHandler updateProfileHandler;

    ///////////////////////////////////////////////
    // Songs Handlers
    ///////////////////////////////////////////////
    final private AddSongHandler addSongHandler;
    final private UpdateSongHandler updateSongHandler;
    final private ClearFavoriteSongHandler clearFavoriteSongHandler;
    final private RemoveSongHandler removeSongHandler;
    final private SetFavoriteSongHandler setFavoriteSongHandler;
    final private QuerySongsHandler querySongsHandler;
    final private ShareSongHandler shareSongHandler;
    final private FindMyManagersHandler findMyManagersHandler;


    @Override
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        return createUserHandler.createUser(createUserCommand);
    }

    @Override
    public FindUsersResponse findAll() {
        return findUsersHandler.findAll();
    }

    @Override
    public FindUserByIdResponse findUserById(UUID id) {
        return findUserByIdHandler.find(id);
    }

    @Override
    public UpdateUserResponse updateUserByAdmin(UpdateUserCommand updateUserCommand) {
        return updateUserHandler.updateUser(updateUserCommand);
    }

    @Override
    public DeleteUserResponse deleteUserByAdmin(UUID id) {
        return deleteUserHandler.deleteUser(id);
    }

    @Override
    public ChangeRoleResponse addCoach(String token, UUID clubId, UUID userId) {
        return changeRoleHandler.addCoach(token,clubId,userId);
    }

    @Override
    public ChangeRoleResponse removeCoach(String token, UUID clubId, UUID userId) {
        return changeRoleHandler.removeCoach(token,clubId,userId);
    }

    @Override
    public void removeAllCoach(String token, UUID clubId) {
         changeRoleHandler.removeAllCoach(token,clubId);
    }

    @Override
    public FindUsersResponse findMyUsers(String token, UUID clubId) {
        return findMyUsersHandler.find(token, clubId);
    }

    @Override
    public FindUsersResponse findCoachesUsers(String token, UUID clubId) {
        return findCoachesHandler.find(token,clubId);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationCommand authenticationCommand) {
        return authenticateHandler.login(authenticationCommand);
    }

    @Override
    public AuthenticationResponse register(RegisterCommand registerCommand) {
        return registerHandler.register(registerCommand);
    }

    @Override
    public AuthenticationResponse refreshToken(String authHeader) {
        return refreshTokenHandler.refreshToken(authHeader);
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordCommand resetPasswordCommand) {
        return resetPasswordHandler.resetPassword(resetPasswordCommand);
    }

    @Override
    public UpdatePasswordResponse updatePassword(String token, UpdatePasswordCommand updatePasswordCommand) {
        return updatePasswordHandler.update(token, updatePasswordCommand);
    }

    @Override
    public void changePassword(String token, ChangePasswordCommand changePasswordCommand) {
        changePasswordHandler.update(token,changePasswordCommand);
    }

    @Override
    public void verifyAccount() {

    }

    @Override
    public void clearDevice(String token, Integer id) {
        updateProfileHandler.removeDevice(token,id);
    }

    @Override
    public UserProfileResponse getProfileInfo(String token) {
        return getProfileInfoHandler.getInfo(token);
    }

    @Override
    public UserProfileResponse updateProfileInfo(String token, UpdateUserProfileCommand updateUserProfileCommand) {
        return updateProfileHandler.updateInfo(token,updateUserProfileCommand);
    }

    @Override
    public FindUsersResponse findMyManagers(String token) {
        return findMyManagersHandler.find(token);
    }

    @Override
    public AddSongResponse addSong(String token, String name, SongMetaData songMetaData) {
        return addSongHandler.addSong(token, name, songMetaData);
    }

    @Override
    public RemoveSongResponse removeSong(String token, UUID songId) {
        return removeSongHandler.removeSong(token,songId);
    }

    @Override
    public SetFavoriteResponse setFavoriteSong(String token, UUID songId) {
        return setFavoriteSongHandler.setFavorite(token,songId);
    }

    @Override
    public ClearFavoriteResponse clearFavoriteSong(String token, UUID songId) {
        return clearFavoriteSongHandler.clearFavorite(token,songId);
    }

    @Override
    public QuerySongResponse getSongs(String token, Boolean isOwned, Boolean isFavorite) {
        return querySongsHandler.querySongs(token,isOwned, isFavorite);
    }

    @Override
    public void share(String token, UUID songId, UUID userId) {
        shareSongHandler.shareSong(token,songId,userId);
    }

    @Override
    public void updateName(String token, UUID songId, UpdateName updateName) {
        updateSongHandler.updateName(token,songId,updateName);
    }



}
