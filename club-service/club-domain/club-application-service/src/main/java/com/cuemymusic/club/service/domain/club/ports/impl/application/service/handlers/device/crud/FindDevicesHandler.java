package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.device.crud;

import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceListResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.FindDevicesByClub;
import com.cuemymusic.club.service.domain.club.mapper.device.DeviceDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.device.DeviceRepository;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FindDevicesHandler {
    private final DeviceRepository deviceRepository;
    private final ClubRepository clubRepository;
    private final DeviceDataMapper deviceDataMapper;

    public FindDevicesHandler(DeviceRepository deviceRepository,
                              ClubRepository clubRepository,
                              DeviceDataMapper deviceDataMapper) {
        this.deviceRepository = deviceRepository;
        this.clubRepository = clubRepository;
        this.deviceDataMapper = deviceDataMapper;
    }


    public DeviceListResponse findAll(UUID clubId) {
        if(!clubRepository.isPresentById(clubId)){
            throw new ClubDomainException("Club doesn't exist");
        }
        return new DeviceListResponse(
                deviceRepository.findAllByClubId(clubId)
                        .stream()
                        .map(deviceDataMapper::deviceToDeviceDTO)
                        .toList()
        );
    }
    public DeviceListResponse findAll() {

        return new DeviceListResponse(
                deviceRepository.findAll()
                        .stream()
                        .map(deviceDataMapper::deviceToDeviceDTO)
                        .toList()
        );
    }


}
