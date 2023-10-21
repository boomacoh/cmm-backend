package com.cuemymusic.firmware.service.application.rest;
import com.cuemymusic.firmware.service.application.service.FileStorage;
import com.cuemymusic.firmware.service.domain.dto.FirmwareCreateCommand;
import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.dto.FirmwareListResponse;
import com.cuemymusic.firmware.service.domain.exceptions.FirmwareDomainException;
import com.cuemymusic.firmware.service.domain.ports.input.FirmwareApplicationServices;
import com.cuemymusic.user.service.domain.exceptions.DomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/device/firmware")
@PreAuthorize("hasAnyRole('DEVICE','ADMIN')")
@AllArgsConstructor
public class FirmwareDeviceController {
    private final FirmwareApplicationServices firmwareApplicationServices;
    FileStorage storageService;

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('admin:read','device:access')")
    public ResponseEntity<FirmwareDto> getCurrent(
    ){
        FirmwareDto querySongResponse =  firmwareApplicationServices.getCurrent();
        return ResponseEntity.ok(querySongResponse);
    }

}
