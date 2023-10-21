package com.cuemymusic.user.service.domain.valueobject;

import com.cuemymusic.user.service.domain.entity.BaseEntity;

import java.util.UUID;

public class FirmwareId extends BaseId<UUID> {

    public FirmwareId(UUID value) {
        super(value);
    }
}
