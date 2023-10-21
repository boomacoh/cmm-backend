package com.cuemymusic.firmware.service.domain.entity.builder;

import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

public class FirmwareBuilder {
    private FirmwareId id;
    private User user;
    private Integer version;
    private String name;
    private String location;
    private Boolean enabled;

    public FirmwareBuilder setId(FirmwareId id) {
        this.id = id;
        return this;
    }

    public FirmwareBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public FirmwareBuilder setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public FirmwareBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FirmwareBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public FirmwareBuilder setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Firmware createFirmware() {
        return new Firmware(id, user, version, name, location, enabled);
    }
}