package com.cuemymusic.firmware.service.dataaccess.firmware.mapper;

import com.cuemymusic.firmware.service.dataaccess.firmware.entity.FirmwareEntity;
import com.cuemymusic.firmware.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.entity.builder.FirmwareBuilder;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import org.springframework.stereotype.Component;

@Component
public class FirmwareDataAccessMapper {
    private final UserDataAccessMapper userDataAccessMapper;

    public FirmwareDataAccessMapper(UserDataAccessMapper userDataAccessMapper) {
        this.userDataAccessMapper = userDataAccessMapper;
    }

    public Firmware firmwareEntityFirmware(FirmwareEntity firmwareEntity){
        return new FirmwareBuilder()
                .setId(new FirmwareId(firmwareEntity.getId()))
                .setLocation(firmwareEntity.getLocation())
                .setEnabled(firmwareEntity.getEnabled())
                .setVersion(firmwareEntity.getVersion())
                .setName(firmwareEntity.getName())
                .setUser(userDataAccessMapper.userEntityToUser(firmwareEntity.getUser()))
                .createFirmware();
    }
    public FirmwareEntity firmwareToFirmwareEntity(Firmware firmware){
        return FirmwareEntity.builder()
                .id(firmware.getId().getValue())
                .name(firmware.getName())
                .version(firmware.getVersion())
                .location(firmware.getLocation())
                .enabled(firmware.getEnabled())
                .user(userDataAccessMapper.userToUserEntity(firmware.getUser()))
                .build();
    }
}
