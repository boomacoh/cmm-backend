package com.cuemymusic.club.service.domain.club.dto.club.management;

import java.util.UUID;

public record DisableUserCommand(UUID clubId, UUID userId) {
}
