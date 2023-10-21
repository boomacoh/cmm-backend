package com.cuemymusic.user.service.domain.dto.user.adminstration.create;


import java.time.ZonedDateTime;
import java.util.UUID;

public record CreateUserResponse(UUID userId, ZonedDateTime zonedDateTime) {
}
