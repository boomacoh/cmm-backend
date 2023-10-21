package com.cuemymusic.user.service.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    private ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
