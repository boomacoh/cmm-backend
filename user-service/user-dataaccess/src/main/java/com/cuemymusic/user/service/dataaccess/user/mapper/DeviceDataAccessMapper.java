package com.cuemymusic.user.service.dataaccess.user.mapper;

import com.cuemymusic.user.service.dataaccess.user.entity.DeviceEntity;
import com.cuemymusic.user.service.dataaccess.user.entity.RankedDeviceEntity;
import com.cuemymusic.user.service.dataaccess.user.entity.RankedDeviceEntityId;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.RankedDeviceBuilder;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.RankedDeviceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeviceDataAccessMapper {
    private UserDataAccessMapper userDataAccessMapper;
    public void setUserDataAccessMapper(UserDataAccessMapper userDataAccessMapper) {
        this.userDataAccessMapper = userDataAccessMapper;
    }
    public RankedDeviceEntity rankedDeviceToRankedDeviceEntityHelper(RankedDevice rankedDevice){
        return RankedDeviceEntity.builder()
                .id(RankedDeviceEntityId.builder()
                        .deviceId(rankedDevice.getDevice().getDeviceId().getValue())
                        .userId(rankedDevice.getUser().getId().getValue())
                        .build())
                .device(DeviceEntity.builder()
                        .id(rankedDevice.getDevice().getDeviceId().getValue())
                        .name(rankedDevice.getDevice().getName())
                        .build())
                .rank(rankedDevice.getRank())
                .build();
    }
    public RankedDevice rankedDeviceEntityToRankedDeviceHelper(RankedDeviceEntity rankedDeviceEntity){
        return new RankedDeviceBuilder()
                .setDevice(
                    new Device(
                        new DeviceId(rankedDeviceEntity.getDevice().getId()),
                        rankedDeviceEntity.getDevice().getName()
                    )
                )
                .setRank(rankedDeviceEntity.getRank())
                .createRankedDevice();
    }

    public RankedDeviceEntity rankedDeviceToRankedDeviceEntity(RankedDevice rankedDevice) {
        final RankedDeviceEntity rankedDeviceEntity = rankedDeviceToRankedDeviceEntityHelper(rankedDevice);
        final UserEntity user = userDataAccessMapper.userToUserEntityHelper(rankedDevice.getUser());
        rankedDeviceEntity.setUser(user);
        return rankedDeviceEntity;
    }

    public RankedDevice rankedDeviceEntityToRankedDevice(RankedDeviceEntity rankedDeviceEntity){
        final RankedDevice rankedDevice = rankedDeviceEntityToRankedDeviceHelper(rankedDeviceEntity);
        final User user = userDataAccessMapper.userEntityToUserHelper(rankedDeviceEntity.getUser());
        rankedDevice.setUser(user);
        return rankedDevice;
    }



}
