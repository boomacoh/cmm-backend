package com.cuemymusic.firmware.service.domain.ports.impl.handlers;

import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.dto.FirmwareListResponse;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.mapper.FirmwareDataMapper;
import com.cuemymusic.firmware.service.domain.ports.output.repository.firmware.FirmwareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class FindAllHandler {
    private final FirmwareRepository firmwareRepository;
    private final FirmwareDataMapper firmwareDataMapper;


    public FindAllHandler(FirmwareRepository firmwareRepository, FirmwareDataMapper firmwareDataMapper) {
        this.firmwareRepository = firmwareRepository;
        this.firmwareDataMapper = firmwareDataMapper;
    }


    @Transactional
    public FirmwareListResponse findAll() {
        List<Firmware> firmwares = firmwareRepository.findAll();
        List<FirmwareDto> res = firmwares.stream().map(firmwareDataMapper::firmwareToFirmwareDto).toList();
        return new FirmwareListResponse(res);
    }

}
