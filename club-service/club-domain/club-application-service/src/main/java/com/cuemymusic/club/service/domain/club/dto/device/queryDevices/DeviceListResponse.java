package com.cuemymusic.club.service.domain.club.dto.device.queryDevices;

import lombok.Builder;

import java.util.List;

@Builder
public record DeviceListResponse(
        List<DeviceDTO> devices
) {}
