package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public record UserRegisteredEvent(User user, ZonedDateTime registeredAt) implements DomainEvent<User> {
}
