package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.time.ZonedDateTime;

public class DeviceAddedEvent  extends UserAuthenticatedEvent {
    private final DeviceId deviceId;
    private final UserId userId;
    private final ZonedDateTime addedAt;


    public DeviceAddedEvent(DeviceId deviceId, UserId userId, ZonedDateTime addedAt) {
        super(userId, addedAt);
        this.deviceId = deviceId;
        this.userId = userId;
        this.addedAt = addedAt;
    }
}
