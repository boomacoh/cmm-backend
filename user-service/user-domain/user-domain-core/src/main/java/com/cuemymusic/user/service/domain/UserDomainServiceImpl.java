package com.cuemymusic.user.service.domain;

import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.event.*;
import com.cuemymusic.user.service.domain.valueobject.*;
import com.cuemymusic.user.service.domain.entity.builder.ResetPasswordTokenBuilder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class UserDomainServiceImpl implements UserDomainService{
    @Override
    public UserRegisteredEvent register(User user, String password) {
        UserId userId = new UserId(UUID.randomUUID());
        user.setId(userId);
        user.setEnabled(true);
        user.setRole(Role.USER);
        user.setSubscription(Subscription.Expired);
        user.setPassword(password);
        user.setTokens(List.of());
        user.setSongs(List.of());
        user.setDevices(user.getDevices());
        return new UserRegisteredEvent(user,ZonedDateTime.now());
    }

    @Override
    public SongAddedEvent addSong(User user, Song song) {
        return new SongAddedEvent(song.getId(),user.getId(), ZonedDateTime.now());
    }

    @Override
    public SongRemovedEvent removeSongById(User user, SongId songId) {
        user.removeSong(songId);
        return new SongRemovedEvent(songId, user.getId(), ZonedDateTime.now());
    }

    @Override
    public DeviceAddedEvent addDevice(User user, RankedDevice device) {
        return new DeviceAddedEvent(device.getId(), user.getId(), ZonedDateTime.now());
    }

    @Override
    public DeviceRemovedEvent removeDevice(User user, DeviceId deviceId) {
        return new DeviceRemovedEvent(deviceId,user.getId(),ZonedDateTime.now());
    }

    @Override
    public UserRoleChangedEvent changeRole(User admin, User user, Role userRole) {
        user.setRole(userRole);
        return new UserRoleChangedEvent(admin.getId(),user.getId(),ZonedDateTime.now(),userRole);
    }

    @Override
    public ResetPasswordTokenCreatedEvent createPasswordResetTokenForUser(User user) {
        TokenId id = new TokenId(UUID.randomUUID());
        String token = UUID.randomUUID().toString();
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime tomorrow = today.plusDays(1);
        ResetPasswordToken resetPasswordToken = new ResetPasswordTokenBuilder()
                .setId(id)
                .setToken(token)
                .setUser(user)
                .setExpiryDate(tomorrow)
                .setRevoked(false)
                .createResetPasswordToken();

        return new ResetPasswordTokenCreatedEvent(resetPasswordToken,user.getId(),ZonedDateTime.now());
    }
}
