package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud;
import com.cuemymusic.club.service.domain.club.dto.device.deleteDevice.DeleteDeviceResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class DeleteDeviceHandler {

    private final DeviceRepository deviceRepository;
    private final DeviceDataMapper deviceDataMapper;

    public DeleteDeviceHandler(DeviceRepository deviceRepository, DeviceDataMapper deviceDataMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDataMapper = deviceDataMapper;
    }

    @Transactional
    public DeleteDeviceResponse deleteDevice(UUID id) {
        delete(id);
        return DeleteDeviceResponse.builder()
                .success(true)
                .deletedDeviceId(id)
                .build();
    }


    private Boolean delete(UUID deviceId) throws ClubDomainException {
        try {
            deviceRepository.delete(deviceId);
            log.info("Device with ID: " + deviceId + " deleted.");
            return true;
        }catch (Exception e){
            throw new ClubDomainException("Failed to Delete Device ID: "+ deviceId);
        }
    }

}
