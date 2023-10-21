package com.cuemymusic.firmware.service.domain.ports.output.repository.common;

import com.cuemymusic.firmware.service.domain.entity.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
    User findByEmail(String email);
    Boolean isPresentByEmail(String email);
    Boolean isPresentById(UUID email);
}
