package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.deleteDevice.DeleteDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceListResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.FindDevicesByClub;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.UserDeviceListResponse;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceResponse;
import com.cuemymusic.club.service.domain.club.ports.input.service.DeviceApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/public/devices", produces = "application/vnd.api.v1+json")
@AllArgsConstructor
public class DeviceListController {
    private final DeviceApplicationService deviceApplicationService;
    @GetMapping("/{clubId}")
    public ResponseEntity<DeviceListResponse> findByClub(@PathVariable UUID clubId){
        DeviceListResponse deviceListResponse = deviceApplicationService.findAll(clubId);
        return ResponseEntity.ok(deviceListResponse);
    }
    @GetMapping("/")
    public ResponseEntity<UserDeviceListResponse> findAll(){
        UserDeviceListResponse deviceListResponse = deviceApplicationService.findAllByUser();
        return ResponseEntity.ok(deviceListResponse);
    }
}
