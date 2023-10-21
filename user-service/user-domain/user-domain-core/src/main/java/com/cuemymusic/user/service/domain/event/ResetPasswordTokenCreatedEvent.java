package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.entity.ResetPasswordToken;
import com.cuemymusic.user.service.domain.valueobject.TokenId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.time.ZonedDateTime;

public record ResetPasswordTokenCreatedEvent(
        ResetPasswordToken token,
        UserId userId,
        ZonedDateTime createdAt
) implements DomainEvent<TokenId> {}
