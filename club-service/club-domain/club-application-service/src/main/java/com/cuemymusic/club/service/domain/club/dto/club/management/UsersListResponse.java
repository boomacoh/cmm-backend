package com.cuemymusic.club.service.domain.club.dto.club.management;

import com.cuemymusic.club.service.domain.club.dto.common.UserDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record UsersListResponse (List<UserDTO> users) {
}
