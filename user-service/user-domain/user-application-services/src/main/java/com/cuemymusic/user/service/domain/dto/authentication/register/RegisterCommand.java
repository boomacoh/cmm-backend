package com.cuemymusic.user.service.domain.dto.authentication.register;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cuemymusic.user.service.domain.valueobject.Role;

@Builder
public record RegisterCommand (
        String firstname,
        String lastname,
        String email,
        String password,
//){
//    private Role role;
        DeviceDTO device
){}