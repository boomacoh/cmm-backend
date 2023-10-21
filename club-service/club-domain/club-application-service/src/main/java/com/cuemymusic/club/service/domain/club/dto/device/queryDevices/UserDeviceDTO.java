package com.cuemymusic.club.service.domain.club.dto.device.queryDevices;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record UserDeviceDTO(
        UUID id,
        String name
) {
}