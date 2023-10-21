package com.cuemymusic.firmware.service.domain.ports.impl;

import com.cuemymusic.firmware.service.domain.dto.FirmwareCreateCommand;
import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.dto.FirmwareListResponse;
import com.cuemymusic.firmware.service.domain.ports.impl.handlers.*;
import com.cuemymusic.firmware.service.domain.ports.input.FirmwareApplicationServices;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmwareApplicationServicesImpl implements FirmwareApplicationServices {
    private final CreateFirmwareHandler createFirmwareHandler;
    private final FindAllHandler findAllHandler;
    private final FindByIdHandler findByIdHandler;
    private final GetCurrentHandler getCurrentHandler;
    private final PromoteByIdHandler promoteByIdHandler;

    public FirmwareApplicationServicesImpl(CreateFirmwareHandler createFirmwareHandler,
                                           FindAllHandler findAllHandler,
                                           FindByIdHandler findByIdHandler,
                                           GetCurrentHandler getCurrentHandler,
                                           PromoteByIdHandler promoteByIdHandler) {
        this.createFirmwareHandler = createFirmwareHandler;
        this.findAllHandler = findAllHandler;
        this.findByIdHandler = findByIdHandler;
        this.getCurrentHandler = getCurrentHandler;
        this.promoteByIdHandler = promoteByIdHandler;
    }

    @Override
    public FirmwareListResponse findAll() {
        return findAllHandler.findAll();
    }
    @Override
    public FirmwareDto create(String token, FirmwareCreateCommand firmwareCreateCommand) {
        return createFirmwareHandler.addFirmware(token,firmwareCreateCommand);
    }

    @Override
    public FirmwareDto findById(UUID id) {
        return findByIdHandler.findById(id);
    }

    @Override
    public FirmwareDto getCurrent() {
        return getCurrentHandler.getCurrentFirmware();
    }
    @Override
    public void promoteById(UUID id) {
        promoteByIdHandler.promote(id);
    }
}
