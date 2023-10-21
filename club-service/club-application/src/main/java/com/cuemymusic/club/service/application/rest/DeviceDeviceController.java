package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.deleteDevice.DeleteDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceListResponse;
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
@RequestMapping(value = "/api/device/", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('DEVICE')")
@AllArgsConstructor
public class DeviceDeviceController {
    private final DeviceApplicationService deviceApplicationService;
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<DeviceListResponse> findAll(){
        DeviceListResponse deviceListResponse = deviceApplicationService.findAll();
        return ResponseEntity.ok(deviceListResponse);
    }
}
