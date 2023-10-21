package com.cuemymusic.firmware.service.domain.mapper;


import com.cuemymusic.firmware.service.domain.dto.FirmwareCreateCommand;
import com.cuemymusic.firmware.service.domain.dto.FirmwareDto;
import com.cuemymusic.firmware.service.domain.dto.UserDTO;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.firmware.service.domain.entity.builder.FirmwareBuilder;
import org.springframework.stereotype.Component;

@Component
public class FirmwareDataMapper {
    public Firmware firmwareCreateCommandToFirmware(FirmwareCreateCommand firmwareCreateCommand){
        return new FirmwareBuilder()
                .setName(firmwareCreateCommand.name())
                .setEnabled(firmwareCreateCommand.enabled())
                .setVersion(firmwareCreateCommand.version())
                .setLocation(firmwareCreateCommand.location())
                .createFirmware();
    }
    public FirmwareDto firmwareToFirmwareDto(Firmware firmware){
        return FirmwareDto.builder()
                .id(firmware.getId().getValue())
                .user(userToUserDto(firmware.getUser()))
                .name(firmware.getName())
                .version(firmware.getVersion())
                .location(firmware.getLocation())
                .enabled(firmware.getEnabled())
                .build();
    }
    private UserDTO userToUserDto(User user){
        return UserDTO.builder()
                .id(user.getUserId().getValue())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
