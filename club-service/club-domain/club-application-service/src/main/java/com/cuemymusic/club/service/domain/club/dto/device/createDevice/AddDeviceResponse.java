package com.cuemymusic.club.service.domain.club.dto.device.createDevice;

import com.cuemymusic.club.service.domain.club.dto.common.UserDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import lombok.Builder;

@Builder
public record AddDeviceResponse(
    DeviceDTO deviceDTO
) {}
