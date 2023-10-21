package com.cuemymusic.user.service.domain;

import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.event.*;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.SongId;

import java.util.UUID;

public interface UserDomainService {
    UserRegisteredEvent register(User user, String password);
    SongAddedEvent addSong(User user, Song song);
    SongRemovedEvent removeSongById(User user, SongId songId);
    DeviceAddedEvent addDevice(User user, RankedDevice device);
    DeviceRemovedEvent removeDevice(User user, DeviceId deviceId);
    UserRoleChangedEvent changeRole(User admin, User user, Role role);
    ResetPasswordTokenCreatedEvent createPasswordResetTokenForUser(User user);

}
