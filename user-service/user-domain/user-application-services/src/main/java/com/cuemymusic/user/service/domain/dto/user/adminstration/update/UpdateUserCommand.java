package com.cuemymusic.user.service.domain.dto.user.adminstration.update;

import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;

import java.util.UUID;

public record UpdateUserCommand(
        UUID id,
        String firstName,
        String lastName,
        Subscription subscription,
        Role role
) {
}
