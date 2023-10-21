package com.cuemymusic.user.service.domain.dto.user.profile;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;
import lombok.Builder;
import com.cuemymusic.user.service.domain.entity.Song;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserProfileResponse {
    UserDTO userDTO;
    List<DeviceDTO> devices;
}
