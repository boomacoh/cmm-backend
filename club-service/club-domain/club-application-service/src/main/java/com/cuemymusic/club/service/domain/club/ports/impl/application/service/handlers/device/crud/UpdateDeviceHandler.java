package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud;

import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class UpdateDeviceHandler {

    private final DeviceDataMapper deviceDataMapper;
    private final DeviceRepository deviceRepository;
    private final ClubRepository clubRepository;

    public UpdateDeviceHandler(DeviceDataMapper deviceDataMapper,
                               DeviceRepository deviceRepository,
                               ClubRepository clubRepository) {
        this.deviceDataMapper = deviceDataMapper;
        this.deviceRepository = deviceRepository;
        this.clubRepository = clubRepository;
    }


    @Transactional
    public UpdateDeviceResponse updateDevice(UpdateDeviceCommand updateDeviceCommand) {
        UUID deviceId = updateDeviceCommand.id();
        if(!deviceRepository.isPresentById(deviceId)){
            throw new ClubDomainException("Club not found");
        }
        Device originalDevice = deviceRepository.findById(deviceId);
        Club club = clubRepository.findById(updateDeviceCommand.clubId());
        Device device = deviceDataMapper.updateDeviceCommandToDevice(updateDeviceCommand);
        device.setClub(club);
        Device result = updateDevice(device,originalDevice);
        return deviceDataMapper.deviceToUpdateDeviceResponse(result);
    }

    private Device saveClub(Device device){
        Device result = deviceRepository.save(device);
        if(result == null){
            throw new DomainException("Failed to update Device with name: "+ device.getName());
        }
        return result;
    }
    private Device updateDevice(Device device, Device originalDevice){
        originalDevice.setClub(device.getClub());
        originalDevice.setDhcp(device.getDhcp());
        originalDevice.setConnectionInterface(device.getConnectionInterface());
        originalDevice.setDns(device.getDns());
        originalDevice.setName(device.getName());
        originalDevice.setGateway(device.getGateway());
        originalDevice.setEnabled(device.getEnabled());
        originalDevice.setIpAddress(device.getIpAddress());
        originalDevice.setZonedDateTime(device.getZonedDateTime());
        originalDevice.setWifiPassword(originalDevice.getWifiPassword());
        originalDevice.setWifiSsid(originalDevice.getWifiSsid());
        originalDevice.setSubnet(device.getSubnet());
        Device result = saveClub(originalDevice);
        return result;
    }

}
