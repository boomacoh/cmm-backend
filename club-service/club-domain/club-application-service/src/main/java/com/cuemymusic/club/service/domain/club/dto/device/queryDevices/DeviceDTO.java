package com.cuemymusic.club.service.domain.club.dto.device.queryDevices;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record DeviceDTO(
        UUID id,
        String name,
        ZonedDateTime zonedDateTime,
        String wifiSsid,
        String connectionInterface,
        String wifiPassword,
        String ipAddress,
        Boolean isDhcp,
        String subnet,
        String gateway,
        String dns,
        Boolean enabled,
        String clubName,
        UUID clubId
) {
}