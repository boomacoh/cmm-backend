package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.deleteDevice.DeleteDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceListResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.FindDevicesByClub;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceResponse;
import com.cuemymusic.club.service.domain.club.ports.input.service.DeviceApplicationService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/devices", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class DeviceController {
    private final DeviceApplicationService deviceApplicationService;
    private final QRCodeGenerator qrCodeGenerator;
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AddDeviceResponse> createUser(@RequestBody AddDeviceCommand addDeviceCommand){
        AddDeviceResponse addDeviceResponse = deviceApplicationService.createDevice(addDeviceCommand);
        return ResponseEntity.ok(addDeviceResponse);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<DeviceListResponse> findAll(){
        DeviceListResponse deviceListResponse = deviceApplicationService.findAll();
        return ResponseEntity.ok(deviceListResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<DeviceDTO> findById(@PathVariable("id") UUID id){
        DeviceDTO deviceDTO = deviceApplicationService.findById(id);
        return ResponseEntity.ok(deviceDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<DeleteDeviceResponse> deleteUserById(@PathVariable("id") UUID id){
        DeleteDeviceResponse deleteDeviceResponse = deviceApplicationService.deleteDevice(id);
        return ResponseEntity.ok(deleteDeviceResponse);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UpdateDeviceResponse> updateUser(@RequestBody UpdateDeviceCommand updateDeviceCommand){
        UpdateDeviceResponse updateDeviceResponse = deviceApplicationService.updateDevice(updateDeviceCommand);
        return ResponseEntity.ok(updateDeviceResponse);
    }
    @GetMapping(value = "/{id}/qr",produces = MediaType.IMAGE_PNG_VALUE)
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<BufferedImage> getQR(@PathVariable("id") UUID id){
        try {
            return ResponseEntity.ok(qrCodeGenerator.getQRCodeImage(id.toString()));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
