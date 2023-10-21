package com.cuemymusic.club.service.domain.club.dto.common;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(
        UUID id,
        String email,
        String firstName,
        String lastName
) {}
