package com.cuemymusic.user.service.domain.dto.user.management.update.roles;

import com.cuemymusic.user.service.domain.valueobject.Role;

import java.time.ZonedDateTime;
import java.util.UUID;


public record ChangeRoleResponse(UUID userId, Role roles, ZonedDateTime zonedDateTime) {}
