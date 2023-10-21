package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.time.ZonedDateTime;


public class UserRoleChangedEvent extends UserAuthenticatedEvent {
    private final UserId adminId;
    private final UserId userId;
    private final ZonedDateTime addedAt;
    private final Role newValue;


    public UserRoleChangedEvent(UserId adminId, UserId userId, ZonedDateTime addedAt, Role newValue) {
        super(userId, addedAt);
        this.adminId = adminId;
        this.userId = userId;
        this.addedAt = addedAt;
        this.newValue = newValue;
    }
}
