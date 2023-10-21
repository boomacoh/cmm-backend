package com.cuemymusic.user.service.domain.dto.user.management.update.roles;

import java.util.UUID;


public record ChangeRoleCommand(UUID userId, Boolean isCoach) {}
