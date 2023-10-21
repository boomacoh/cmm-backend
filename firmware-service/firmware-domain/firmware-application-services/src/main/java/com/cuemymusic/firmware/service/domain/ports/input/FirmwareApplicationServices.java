package com.cuemymusic.firmware.service.domain.ports.input;

import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.dto.FirmwareCreateCommand;
import com.cuemymusic.firmware.service.domain.dto.FirmwareListResponse;

import java.util.UUID;

public interface FirmwareApplicationServices {
    FirmwareListResponse findAll();
    FirmwareDto create(String token, FirmwareCreateCommand firmwareCreateCommand);
    FirmwareDto findById(UUID id);
    FirmwareDto getCurrent();
    void promoteById(UUID id);
}
