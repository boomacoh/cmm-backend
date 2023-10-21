package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.valueobject.UserId;
import com.cuemymusic.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserRefreshedTokenEvent implements DomainEvent<User> {
    private final UserId userId;
    private final ZonedDateTime refreshedAt;


    public UserRefreshedTokenEvent(UserId userId, ZonedDateTime refreshedAt) {
        this.userId = userId;
        this.refreshedAt = refreshedAt;
    }
}
