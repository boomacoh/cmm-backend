package com.cuemymusic.user.service.domain.ports.output.repository.admin;

import com.cuemymusic.user.service.domain.entity.RankedDevice;

import java.util.Optional;
import java.util.UUID;

public interface DeviceAdminRepository {
    Optional<RankedDevice> getBySearchId(UUID searchId);

}
