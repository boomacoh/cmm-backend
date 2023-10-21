package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class CreateDeviceHandler {
    private final DeviceRepository deviceRepository;
    private final DeviceDataMapper deviceDataMapper;
    private final ClubRepository clubRepository;
    public CreateDeviceHandler(DeviceRepository deviceRepository,
                               DeviceDataMapper deviceDataMapper,
                               ClubRepository clubRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceDataMapper = deviceDataMapper;
        this.clubRepository = clubRepository;
    }


    @Transactional
    public AddDeviceResponse createDevice(AddDeviceCommand addDeviceCommand) {
        Device device = deviceDataMapper.addDeviceCommandToDevice(addDeviceCommand);
        Club club = clubRepository.findById(addDeviceCommand.clubId());
        device.setPlaylist(List.of());
        device.setClub(club);
        Device result = saveClub(device);
        deviceRepository.addAdminToDevice(club.getAdminId().getValue(),device.getId().getValue());
        return deviceDataMapper.deviceToAddDeviceResponse(result);
    }

    private Device saveClub(Device device){
        Device result = deviceRepository.save(device);
        if(result == null){
            throw new DomainException("Failed to Create New Club with name: "+ device.getName());
        }
        return result;
    }

}
