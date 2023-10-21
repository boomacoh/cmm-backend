package com.cuemymusic.user.service.domain.ports.input.service;

import com.cuemymusic.user.service.domain.dto.authentication.password.change.ChangePasswordCommand;
import com.cuemymusic.user.service.domain.dto.authentication.authenticate.AuthenticationCommand;
import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
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
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import jakarta.validation.Valid;

import java.util.UUID;

public interface UserApplicationService {
    ////////////////////////////////////
    // CRUD Admin endpoints ############
    ////////////////////////////////////
    CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);

    FindUsersResponse findAll();

    FindUserByIdResponse findUserById(@Valid UUID id);

    UpdateUserResponse updateUserByAdmin(@Valid UpdateUserCommand updateUserCommand);

    DeleteUserResponse deleteUserByAdmin(@Valid UUID id);


    ///////////////////////////////////
    // Management #####################
    ///////////////////////////////////
    ChangeRoleResponse addCoach(String token, UUID clubId, UUID userId);

    ChangeRoleResponse removeCoach(String token, UUID clubId, UUID userId);

    void removeAllCoach(String token, UUID clubId);

    FindUsersResponse findMyUsers(String token, UUID clubId);
    FindUsersResponse findCoachesUsers(String token, UUID clubId);



    ///////////////////////////////////
    // Authentication #################
    ///////////////////////////////////
    AuthenticationResponse authenticate(@Valid AuthenticationCommand authenticationCommand);

    AuthenticationResponse register(@Valid RegisterCommand registerCommand);

    AuthenticationResponse refreshToken(@Valid String authHeader);

    ResetPasswordResponse resetPassword(@Valid ResetPasswordCommand resetPasswordCommand);

    UpdatePasswordResponse updatePassword(String token, @Valid UpdatePasswordCommand updatePasswordCommand);
    void changePassword(String token, @Valid ChangePasswordCommand changePasswordCommand);
    void verifyAccount();
    void clearDevice(String token, Integer rank);




    ///////////////////////////////////
    // Profile ########################
    ///////////////////////////////////
    UserProfileResponse getProfileInfo(String token);
    UserProfileResponse updateProfileInfo(String token, UpdateUserProfileCommand updateUserProfileCommand);
    FindUsersResponse findMyManagers(String token);


    ///////////////////////////////////////////
    // Songs ##################################
    ///////////////////////////////////////////
    AddSongResponse addSong(String token, String name, @Valid SongMetaData songMetaData);
    RemoveSongResponse removeSong(String token, UUID songID);
    SetFavoriteResponse setFavoriteSong(String token, UUID songID);
    ClearFavoriteResponse clearFavoriteSong(String token, UUID songID);
    QuerySongResponse getSongs(String token, Boolean isOwned, Boolean isFavorite);
    void share(String token, UUID songId, UUID userId);
    void updateName(String token, UUID songId, UpdateName updateName);

}
