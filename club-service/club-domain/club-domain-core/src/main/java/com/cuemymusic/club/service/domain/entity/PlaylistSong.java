package com.cuemymusic.club.service.domain.entity;

import com.cuemymusic.user.service.domain.entity.BaseEntity;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;

import java.time.ZonedDateTime;
import java.util.Objects;

public class PlaylistSong extends BaseEntity<PlaylistSongId> {
    private PlaylistSongId id;
    private User user;
    private Double order;
    private Song song;
    private ZonedDateTime eta;
    private Boolean played;

    public PlaylistSong(PlaylistSongId id, User user, double order, Song song, ZonedDateTime eta, Boolean played) {
        this.id = id;
        this.user = user;
        this.order = order;
        this.song = song;
        this.eta = eta;
        this.played = played;
    }
    @Override
    public PlaylistSongId getId() {
        return id;
    }

    @Override
    public void setId(PlaylistSongId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public ZonedDateTime getEta() {
        return eta;
    }

    public void setEta(ZonedDateTime eta) {
        this.eta = eta;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistSong that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    public Double getOrder() {
        return order;
    }

    public void setOrder(Double order) {
        this.order = order;
    }
}
