package com.cuemymusic.user.service.domain.mapper.users;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;
import com.cuemymusic.user.service.domain.dto.user.profile.UpdateUserProfileCommand;
import com.cuemymusic.user.service.domain.dto.user.profile.UserProfileResponse;
import com.cuemymusic.user.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.RankedDeviceBuilder;
import com.cuemymusic.user.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.RankedDeviceId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserProfileMapper {
    public UserProfileResponse userToUserProfileResponse(User user){
        UserProfileResponse response = UserProfileResponse
                .builder()
                .userDTO(
                        new UserDTO(
                                user.getId().getValue(),
                                user.getEmail(),
                                user.getFirstName(),
                                user.getLastName(),
                                "*******",
                                user.getSubscription(),
                                user.getRole(),
                                user.getDeviceId().getValue()
                        )
                )
                .devices(user.getDevices().stream().map( d ->
                        new DeviceDTO(
                                d.getDevice().getDeviceId().getValue(),
                                d.getDevice().getName(),
                                d.getRank()
                        )
                ).toList())
                .build();
        return response;

    }
    public User updateUserProfileCommandToUser(UpdateUserProfileCommand updateUserProfileCommand, User originalUser){
        User user = new UserBuilder()
                .setFirstName(updateUserProfileCommand.firstName())
                .setLastName(updateUserProfileCommand.lastName())
                .setDevices(updateUserProfileCommand.devices().stream().map(e->this.deviceDtoToDevice(e,originalUser)).toList())
                .createUser();
        return user;
    }

    public RankedDevice deviceDtoToDevice(DeviceDTO deviceDTO, User user){
        return new RankedDeviceBuilder()
                .setDevice(
                        new Device(new DeviceId(deviceDTO.id()),deviceDTO.name())
                )
                .setRank(deviceDTO.rank())
                .setUser(user)
                .createRankedDevice();
    }

}
