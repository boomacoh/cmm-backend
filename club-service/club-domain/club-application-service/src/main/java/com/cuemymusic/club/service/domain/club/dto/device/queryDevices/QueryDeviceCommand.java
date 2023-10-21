package com.cuemymusic.club.service.domain.club.dto.device.queryDevices;

import lombok.Builder;

import java.util.UUID;

@Builder
public record QueryDeviceCommand(
   UUID deviceId,
   String deviceName,
   String clubName
) {}
