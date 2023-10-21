package com.cuemymusic.firmware.service.domain.ports.impl.handlers;

import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.exceptions.FirmwareDomainException;
import com.cuemymusic.firmware.service.domain.mapper.FirmwareDataMapper;
import com.cuemymusic.firmware.service.domain.ports.output.repository.firmware.FirmwareRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetCurrentHandler {
    private final FirmwareRepository firmwareRepository;
    private final FirmwareDataMapper firmwareDataMapper;



    public GetCurrentHandler(FirmwareRepository firmwareRepository, FirmwareDataMapper firmwareDataMapper) {
        this.firmwareRepository = firmwareRepository;
        this.firmwareDataMapper = firmwareDataMapper;
    }


    @Transactional
    public FirmwareDto getCurrentFirmware() {
        Optional<Firmware> firmware = firmwareRepository.getCurrent();
        if(firmware.isEmpty()){
            throw new FirmwareDomainException("No Firmware Available");
        }
//        FirmwareDto res =  FirmwareDto.builder().build();
//
        FirmwareDto res = firmwareDataMapper.firmwareToFirmwareDto(firmware.get());
        return res;
    }
}
