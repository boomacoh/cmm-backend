package com.cuemymusic.user.service.domain.ports.output.repository.common;

import com.cuemymusic.user.service.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User findById(UUID userId);
    User findByEmail(String email);
    Boolean isPresentByEmail(String email);
    Boolean isPresentById(UUID email);
    List<User> findAll();
    void deleteById(UUID userId);
    List<User> findMyManagers(UUID userId) ;
    void clearAllDevices(UUID userId);
    void removeDevice(UUID userId, Integer rank);
}
