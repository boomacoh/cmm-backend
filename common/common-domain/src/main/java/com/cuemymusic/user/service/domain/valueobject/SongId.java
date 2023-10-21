package com.cuemymusic.user.service.domain.valueobject;

import java.util.UUID;

public class SongId extends BaseId<UUID>{

    public SongId(UUID value) {
        super(value);
    }
}
