package com.cuemymusic.user.service.domain.entity.builder;

import com.cuemymusic.user.service.domain.entity.*;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.util.List;

public class UserBuilder {
    private UserId id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Subscription subscription;
    private Role role;
    private ResetPasswordToken resetPasswordToken;
    private Boolean enabled;
    private List<Song> songs;
    private List<Song> favoriteSongs;
    private List<Token> tokens;
    private List<RankedDevice> devices;
    private DeviceId deviceId;

    public UserBuilder setId(UserId id) {
        this.id = id;
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

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setSubscription(Subscription subscription) {
        this.subscription = subscription;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder setResetPasswordToken(ResetPasswordToken resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
        return this;
    }

    public UserBuilder setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder setSongs(List<Song> songs) {
        this.songs = songs;
        return this;
    }

    public UserBuilder setFavoriteSongs(List<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
        return this;
    }

    public UserBuilder setTokens(List<Token> tokens) {
        this.tokens = tokens;
        return this;
    }

    public UserBuilder setDevices(List<RankedDevice> devices) {
        this.devices = devices;
        return this;
    }

    public UserBuilder setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public User createUser() {
        return new User(id, firstName, lastName, email, password, subscription, role, resetPasswordToken, enabled, songs, favoriteSongs, tokens, devices, deviceId);
    }
}