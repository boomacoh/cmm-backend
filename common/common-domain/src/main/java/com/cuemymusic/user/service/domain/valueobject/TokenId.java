package com.cuemymusic.user.service.domain.valueobject;

import java.util.UUID;

public class TokenId extends BaseId<UUID>{
    public TokenId(UUID value) {
        super(value);
    }
}
