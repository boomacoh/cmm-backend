package com.cuemymusic.firmware.service.domain.ports.impl.handlers;

import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.exceptions.FirmwareDomainException;
import com.cuemymusic.firmware.service.domain.mapper.FirmwareDataMapper;
import com.cuemymusic.firmware.service.domain.ports.output.repository.firmware.FirmwareRepository;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class PromoteByIdHandler {
    private final FirmwareRepository firmwareRepository;
    private final FirmwareDataMapper firmwareDataMapper;

    public PromoteByIdHandler(FirmwareRepository firmwareRepository, FirmwareDataMapper firmwareDataMapper) {
        this.firmwareRepository = firmwareRepository;
        this.firmwareDataMapper = firmwareDataMapper;
    }

    @Transactional
    public void promote(UUID id) {
        final FirmwareId firmwareId = new FirmwareId(id);
        if(!firmwareRepository.isPresent(firmwareId)){
            throw new FirmwareDomainException("no firmware found with the given id");
        }
        firmwareRepository.promoteById(firmwareId);
    }
}
