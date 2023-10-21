package com.cuemymusic.firmware.service.domain.entity.builder;


import com.cuemymusic.firmware.service.domain.entity.Token;
import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.util.List;

public class UserBuilder {
    private UserId userId;
    private String email;
    private String firstName;
    private String lastName;
    private List<Token> tokens;
    private Role role;

    public UserBuilder setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setTokens(List<Token> tokens) {
        this.tokens = tokens;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public User createUser() {
        return new User(userId, email, firstName, lastName, tokens, role);
    }
}