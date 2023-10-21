package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud;

import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FindDeviceByIdHandler {
    private final DeviceRepository deviceRepository;
    private final DeviceDataMapper deviceDataMapper;

    public FindDeviceByIdHandler(DeviceRepository deviceRepository, DeviceDataMapper deviceDataMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDataMapper = deviceDataMapper;
    }


    public DeviceDTO find(UUID id) {
        if(!deviceRepository.isPresentById(id)) {
            throw new ClubDomainException("Club doesn't exist");
        }
        Device device =  deviceRepository.findById(id);
        return deviceDataMapper.deviceToDeviceDTO(device);
    }

}
