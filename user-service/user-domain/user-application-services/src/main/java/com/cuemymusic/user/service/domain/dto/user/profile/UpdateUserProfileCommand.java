package com.cuemymusic.user.service.domain.dto.user.profile;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateUserProfileCommand(
    String firstName,
    String lastName,
    List<DeviceDTO> devices
//    String email,
) {

}
