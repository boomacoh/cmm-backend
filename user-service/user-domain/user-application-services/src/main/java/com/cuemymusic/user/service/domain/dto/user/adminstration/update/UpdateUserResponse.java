package com.cuemymusic.user.service.domain.dto.user.adminstration.update;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;

import java.util.List;

public record UpdateUserResponse(UserDTO user, List<DeviceDTO> devices) {
}
