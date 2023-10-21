package com.cuemymusic.club.service.domain.entity.builder;

import com.cuemymusic.club.service.domain.entity.PlaylistSong;
import com.cuemymusic.club.service.domain.entity.Song;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;

import java.time.ZonedDateTime;

public class PlaylistSongBuilder {
    private PlaylistSongId id;
    private User user;
    private Double order;
    private Song song;
    private ZonedDateTime eta;
    private Boolean played;

    public PlaylistSongBuilder setId(PlaylistSongId id) {
        this.id = id;
        return this;
    }

    public PlaylistSongBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public PlaylistSongBuilder setOrder(Double order) {
        this.order = order;
        return this;
    }

    public PlaylistSongBuilder setSong(Song song) {
        this.song = song;
        return this;
    }

    public PlaylistSongBuilder setEta(ZonedDateTime eta) {
        this.eta = eta;
        return this;
    }

    public PlaylistSongBuilder setPlayed(Boolean played) {
        this.played = played;
        return this;
    }

    public PlaylistSong createPlaylistSong() {
        return new PlaylistSong(id, user, order, song, eta, played);
    }
}