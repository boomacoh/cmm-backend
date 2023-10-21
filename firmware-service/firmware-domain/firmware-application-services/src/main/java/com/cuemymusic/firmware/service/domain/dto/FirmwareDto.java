package com.cuemymusic.firmware.service.domain.dto;

import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FirmwareDto(
        UUID id,
        UserDTO user,
        Integer version,
        String name,
        String location,
        Boolean enabled
) {
}
