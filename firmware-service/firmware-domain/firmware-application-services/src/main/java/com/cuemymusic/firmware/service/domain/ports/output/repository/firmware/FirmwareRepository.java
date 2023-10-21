package com.cuemymusic.firmware.service.domain.ports.output.repository.firmware;

import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;

import java.util.List;
import java.util.Optional;

public interface FirmwareRepository {
    List<Firmware> findAll();
    Firmware save(Firmware firmware);
    void deleteById(FirmwareId id);
    Firmware findById(FirmwareId id);
    boolean isPresent(FirmwareId id);
    Optional<Firmware> getCurrent();
    void promoteById(FirmwareId id);
}
