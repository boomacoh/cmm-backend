package com.cuemymusic.firmware.service.domain.ports.impl.handlers;

import com.cuemymusic.firmware.service.domain.dto.FirmwareCreateCommand;
import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.firmware.service.domain.entity.builder.FirmwareBuilder;
import com.cuemymusic.firmware.service.domain.exceptions.FirmwareDomainException;
import com.cuemymusic.firmware.service.domain.mapper.FirmwareDataMapper;
import com.cuemymusic.firmware.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.firmware.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.firmware.service.domain.ports.output.repository.firmware.FirmwareRepository;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class CreateFirmwareHandler {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final FirmwareRepository firmwareRepository;
    private final FirmwareDataMapper firmwareDataMapper;

    public CreateFirmwareHandler(UserRepository userRepository,
                                 AuthenticationService authenticationService,
                                 FirmwareRepository firmwareRepository,
                                 FirmwareDataMapper firmwareDataMapper) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.firmwareRepository = firmwareRepository;
        this.firmwareDataMapper = firmwareDataMapper;
    }


    @Transactional
    public FirmwareDto addFirmware(String token, FirmwareCreateCommand firmwareCreateCommand) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        FirmwareId firmwareId = new FirmwareId(UUID.randomUUID());
        Firmware firmware = firmwareDataMapper.firmwareCreateCommandToFirmware(firmwareCreateCommand);
        firmware.setUser(user);
        firmware.setId(firmwareId);
        firmwareRepository.save(firmware);
        FirmwareDto res = firmwareDataMapper.firmwareToFirmwareDto(firmware);
        return res;
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new FirmwareDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
