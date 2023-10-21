package com.cuemymusic.club.service.domain.club.dto.device.updateDevice;

import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import lombok.Builder;

@Builder
public record UpdateDeviceResponse(
        DeviceDTO deviceDTO
) {
}
