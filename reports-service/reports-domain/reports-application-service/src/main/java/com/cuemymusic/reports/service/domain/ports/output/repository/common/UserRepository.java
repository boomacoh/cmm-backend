package com.cuemymusic.reports.service.domain.ports.output.repository.common;


import com.cuemymusic.reports.service.domain.entity.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
    User findByEmail(String email);
    Boolean isPresentByEmail(String email);
    Boolean isPresentById(UUID email);
}
