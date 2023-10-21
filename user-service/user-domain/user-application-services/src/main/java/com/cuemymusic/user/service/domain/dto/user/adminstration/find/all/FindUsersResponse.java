package com.cuemymusic.user.service.domain.dto.user.adminstration.find.all;

import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;

import java.util.List;

public record FindUsersResponse(List<UserDTO> users) {}