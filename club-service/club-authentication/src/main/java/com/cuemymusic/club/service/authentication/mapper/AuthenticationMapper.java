package com.cuemymusic.club.service.authentication.mapper;

import com.cuemymusic.club.service.authentication.entities.AuthToken;
import com.cuemymusic.club.service.authentication.entities.AuthUser;
import com.cuemymusic.club.service.domain.entity.Token;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationMapper {

    public com.cuemymusic.club.service.authentication.entities.Role domainRoleToAuthRole(Role role){
        return com.cuemymusic.club.service.authentication.entities.Role.valueOf(role.name());
    }
    


    public AuthUser domainUserToAuthUser(User user, List<AuthToken> authTokens) {
        return new AuthUser()
                .builder()
                .id(user.getUserId().getValue())
                .email(user.getEmail())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .role(domainRoleToAuthRole(user.getRole()))
                .build();
    }
    public AuthToken domainTokenToAuthToken(Token token){
        return   new AuthToken()
                .builder()
                .id(token.getId().getValue())
                .tokenType(token.getTokenType())
                .expired(token.isExpired())
                .revoked(token.isRevoked())
                .token(token.getToken())
                .build();

    }
}
