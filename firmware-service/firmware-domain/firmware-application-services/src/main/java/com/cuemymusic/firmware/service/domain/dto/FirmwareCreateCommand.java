package com.cuemymusic.firmware.service.domain.dto;


import com.cuemymusic.firmware.service.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Builder
public record FirmwareCreateCommand(
        Integer version,
        String name,
        String location,
        Boolean enabled
) {
}
