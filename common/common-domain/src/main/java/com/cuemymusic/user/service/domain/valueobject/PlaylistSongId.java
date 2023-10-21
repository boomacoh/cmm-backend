package com.cuemymusic.user.service.domain.valueobject;

import java.util.UUID;

public class PlaylistSongId extends BaseId<UUID>{
    public PlaylistSongId(UUID value) {
        super(value);
    }
}
