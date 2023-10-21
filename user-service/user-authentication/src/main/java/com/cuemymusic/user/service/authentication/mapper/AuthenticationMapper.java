package com.cuemymusic.user.service.authentication.mapper;

import com.cuemymusic.user.service.authentication.entities.AuthToken;
import com.cuemymusic.user.service.authentication.entities.AuthUser;
import com.cuemymusic.user.service.domain.entity.Token;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationMapper {

    public com.cuemymusic.user.service.authentication.entities.Role domainRoleToAuthRole(Role role){
        return com.cuemymusic.user.service.authentication.entities.Role.valueOf(role.name());
    }
    


    public AuthUser domainUserToAuthUser(User user, List<AuthToken> authTokens) {
        return new AuthUser()
                .builder()
                .id(user.getId().getValue())
                .email(user.getEmail())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .password(user.getPassword())
                .tokens(authTokens)
                .enabled(user.getEnabled())
                .role(domainRoleToAuthRole(user.getRole()))
                .build();
    }
    public AuthToken domainTokenToAuthToken(Token token){
        return new AuthToken()
                .builder()
                .id(token.getId().getValue())
                .tokenType(token.getTokenType())
                .expired(token.isExpired())
                .revoked(token.isRevoked())
                .token(token.getToken())
                .build();

    }
}
