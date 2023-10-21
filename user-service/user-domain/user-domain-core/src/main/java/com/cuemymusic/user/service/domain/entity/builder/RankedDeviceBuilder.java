package com.cuemymusic.user.service.domain.entity.builder;

import com.cuemymusic.user.service.domain.entity.Device;
import com.cuemymusic.user.service.domain.entity.RankedDevice;
import com.cuemymusic.user.service.domain.entity.User;

public class RankedDeviceBuilder {
    private Device device;
    private User user;
    private Integer rank;

    public RankedDeviceBuilder setDevice(Device device) {
        this.device = device;
        return this;
    }

    public RankedDeviceBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public RankedDeviceBuilder setRank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public RankedDevice createRankedDevice() {
        return new RankedDevice(device, user, rank);
    }
}