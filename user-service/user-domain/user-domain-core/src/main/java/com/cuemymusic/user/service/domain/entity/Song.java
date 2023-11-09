package com.cuemymusic.user.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.SongId;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public class Song extends BaseEntity<SongId> {
    private SongId id;
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
    public Song(SongId songId,
                UUID owner,
                String name,
                String fileLocation,
                String uploadName,
                String copyRight,
                String title,
                String artist,
                String recordLabel,
                Integer duration,
                Boolean isFavorite,
                Long plays,
                Date createdDateTime,
                Date updatedDateTime,
                String createdBy,
                String updatedBy) {
        this.id = songId;
        this.owner = owner;
        this.name = name;
        this.fileLocation = fileLocation;
        this.uploadName = uploadName;
        this.copyRight = copyRight;
        this.title = title;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.duration = duration;
        this.isFavorite = isFavorite;
        this.plays = plays;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }



    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Long getPlays() {
        return plays;
    }

    public void setPlays(Long plays) {
        this.plays = plays;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + id +
                ", isFavorite=" + isFavorite +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public SongId getId() {
        return id;
    }

    @Override
    public void setId(SongId id) {
        this.id = id;
    }
}
