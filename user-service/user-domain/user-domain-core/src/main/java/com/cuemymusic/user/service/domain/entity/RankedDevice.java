package com.cuemymusic.user.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.RankedDeviceId;

import java.util.UUID;

public class RankedDevice extends BaseEntity<DeviceId> {

    public RankedDevice(Device device, User user, Integer rank) {
        this.device = device;
        this.user = user;
        this.rank = rank;
    }

    private Device device;
    private User user;
    private Integer rank;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
