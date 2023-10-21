package com.cuemymusic.user.service.domain.event;

import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.time.ZonedDateTime;


public class SongRemovedEvent extends UserAuthenticatedEvent {
    private final SongId songId;
    private final UserId userId;
    private final ZonedDateTime addedAt;


    public SongRemovedEvent(SongId songId, UserId userId, ZonedDateTime addedAt) {
        super(userId, addedAt);
        this.songId = songId;
        this.userId = userId;
        this.addedAt = addedAt;
    }
}
