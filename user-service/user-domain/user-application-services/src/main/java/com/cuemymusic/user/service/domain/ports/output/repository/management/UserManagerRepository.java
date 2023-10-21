package com.cuemymusic.user.service.domain.ports.output.repository.management;

import com.cuemymusic.user.service.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserManagerRepository {
    User save(User user);

    Boolean isMyUserPresent(UUID adminId, UUID userId, UUID clubId);
    List<User> findMyUsers(UUID adminId, UUID clubId);

    User findMyUserById(UUID adminId, UUID userId, UUID clubId);

    void assignCoach(UUID adminId, UUID clubId);

    void revokeCoach(UUID adminId, UUID userId);
    void revokeAllCoaches(UUID adminId, UUID clubId);
}
