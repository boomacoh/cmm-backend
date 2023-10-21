package com.cuemymusic.user.service.domain.dto.user.common;


import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;
import java.util.UUID;

public record UserDTO(UUID id, String email, String firstName, String lastName,String password, Subscription subscription, Role role, UUID deviceId){
}