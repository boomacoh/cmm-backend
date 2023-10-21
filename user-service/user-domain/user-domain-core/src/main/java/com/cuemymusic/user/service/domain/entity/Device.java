package com.cuemymusic.user.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.DeviceId;

public class Device {
    private DeviceId deviceId;
    private String name;

    public Device(DeviceId deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
