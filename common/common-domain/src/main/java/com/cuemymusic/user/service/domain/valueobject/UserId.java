package com.cuemymusic.user.service.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class UserId extends BaseId<UUID> {
    public UserId(UUID value) {
        super(value);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId baseId = (UserId) o;
        return Objects.equals(super.getValue(), baseId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getValue());
    }

}
