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
@RequestMapping(value = "/api/admin/firmware")
@PreAuthorize("hasAnyRole('ADMIN')")
@AllArgsConstructor
public class FirmwareAdminController {
    private final FirmwareApplicationServices firmwareApplicationServices;
    FileStorage storageService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<FirmwareDto> uploadSong (
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
        @RequestParam(name = "name", required = true) String name,
        @RequestParam(name = "version", required = true) Integer version,
        @RequestParam("file") MultipartFile file
    ){
        final String token;
        log.warn("File Upload --------------->  : "+authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new FirmwareDomainException("Unauthorized Refresh Token");
        }
        token = authHeader.substring(7);
        try {
            String location = storageService.save(file);
            FirmwareCreateCommand firmwareCreateCommand = FirmwareCreateCommand.builder()
                    .name(name)
                    .location(location)
                    .version(version)
                    .enabled(false)
                    .build();
            FirmwareDto  res =  firmwareApplicationServices.create(token, firmwareCreateCommand);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.warn(e.getStackTrace().toString());
            throw new DomainException("Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<FirmwareListResponse> findAll(){
        FirmwareListResponse querySongResponse =  firmwareApplicationServices.findAll();
        return ResponseEntity.ok(querySongResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<FirmwareDto> findByID(
            @PathVariable UUID id
    ){
        FirmwareDto querySongResponse =  firmwareApplicationServices.findById(id);
        return ResponseEntity.ok(querySongResponse);
    }
    @GetMapping("/promote/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity promoteById(
            @PathVariable UUID id
    ){
        firmwareApplicationServices.promoteById(id);
        return ResponseEntity.ok().build();
    }
}
