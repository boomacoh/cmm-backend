package com.cuemymusic.club.service.domain.club.ports.output.repository.common;
import com.cuemymusic.club.service.domain.entity.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
    User findByEmail(String email);
    Boolean isPresentByEmail(String email);
    Boolean isPresentById(UUID id);
}
