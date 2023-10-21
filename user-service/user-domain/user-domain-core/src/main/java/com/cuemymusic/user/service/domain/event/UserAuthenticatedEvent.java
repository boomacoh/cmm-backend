package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.time.ZonedDateTime;

public class UserAuthenticatedEvent implements DomainEvent<User> {
    private final UserId userId;
    private final ZonedDateTime authenticatedAt;

    public UserAuthenticatedEvent(UserId userId, ZonedDateTime authenticatedAt) {
        this.userId = userId;
        this.authenticatedAt = authenticatedAt;
    }
}
