package com.cuemymusic.firmware.service.domain.dto;


import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(UUID id, String email, String firstName, String lastName){
}