package com.cuemymusic.user.service.domain.mapper.authentication;

import com.cuemymusic.user.service.domain.dto.authentication.authenticate.AuthenticationCommand;
import com.cuemymusic.user.service.domain.dto.authentication.common.AuthenticationResponse;
import com.cuemymusic.user.service.domain.dto.authentication.register.RegisterCommand;
import com.cuemymusic.user.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.builder.RankedDeviceBuilder;
import com.cuemymusic.user.service.domain.mapper.users.UserProfileMapper;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuthenticationDataMapper {
    final UserProfileMapper userProfileMapper;

    public AuthenticationDataMapper(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    public User registerUserCommandToUser(RegisterCommand registerCommand){
        final User user = new  UserBuilder()
                .setFirstName(registerCommand.firstname())
                .setLastName(registerCommand.lastname())
                .setEmail(registerCommand.email())
                .setSubscription(Subscription.Expired)
                .setRole(Role.USER).createUser();
        final RankedDevice device = new RankedDeviceBuilder()
                .setUser(user)
                .setRank(1)
                .setDevice(
                        new Device(
                                new DeviceId(registerCommand.device().id()),
                                registerCommand.device().name()
                        )
                ).createRankedDevice();
        user.setDevices(List.of(device));
        return user;
//                .setDevices(List.of(device))
//                .createUser();
    }

    public User authenticateUserCommandToUser(AuthenticationCommand authenticationCommand){
        return new UserBuilder()
                .setEmail(authenticationCommand.getEmail())
                .setPassword(authenticationCommand.getPassword())
                .createUser();
    }

    public AuthenticationResponse tokensToAuthenticationResponse(Token token, Token refreshToken) {
        return new AuthenticationResponse(token.getToken(),refreshToken.getToken());
    }

}
