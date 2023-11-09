package com.cuemymusic.user.service.domain.entity.builder;

import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.SongId;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public class SongBuilder {
    private SongId songId;
    private UUID owner;
    private String name;
    private String fileLocation;
    private String uploadName;
    private String copyRight;
    private String title;
    private String artist;
    private String recordLabel;
    private Integer duration;
    private Boolean isFavorite;
    private Long plays;
    private Date createdDateTime;
    private Date updatedDateTime;
    private String createdBy;
    private String updatedBy;

    public SongBuilder setSongId(SongId songId) {
        this.songId = songId;
        return this;
    }

    public SongBuilder setOwner(UUID owner) {
        this.owner = owner;
        return this;
    }

    public SongBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SongBuilder setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public SongBuilder setUploadName(String uploadName) {
        this.uploadName = uploadName;
        return this;
    }

    public SongBuilder setCopyRight(String copyRight) {
        this.copyRight = copyRight;
        return this;
    }

    public SongBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SongBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public SongBuilder setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public SongBuilder setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public SongBuilder setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
        return this;
    }

    public SongBuilder setPlays(long plays) {
        this.plays = plays;
        return this;
    }

    public SongBuilder setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }
    public SongBuilder setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
        return this;
    }
    public SongBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
    public SongBuilder setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }
    
    public Song createSong() {
        return new Song(songId, owner, name, fileLocation, uploadName, copyRight, title, artist, recordLabel, duration, isFavorite,plays,createdDateTime,updatedDateTime,createdBy,updatedBy);
    }
}