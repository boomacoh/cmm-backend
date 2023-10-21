package com.cuemymusic.club.service.domain.club.dto.device.deleteDevice;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DeleteDeviceResponse(
        UUID deletedDeviceId,
        Boolean success
) {}
