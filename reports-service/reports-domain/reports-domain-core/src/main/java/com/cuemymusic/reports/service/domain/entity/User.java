package com.cuemymusic.reports.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.util.List;

public class User {
    private UserId userId;
    private String email;
    private String  firstName;
    private String  lastName;
    private List<Token> tokens;
    private Role role;

    public User(UserId userId, String email, String firstName, String lastName, List<Token> tokens, Role role) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tokens = tokens;
        this.role = role;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
