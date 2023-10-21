package com.cuemymusic.user.service.domain.valueobject.builder;

import com.cuemymusic.user.service.domain.valueobject.SongMetaData;

public class SongMetaDataBuilder {
    private String copyRight;
    private String title;
    private String artist;
    private String recordLabel;
    private Integer duration;
    private String uploadFileName;
    private String fileLocation;

    public SongMetaDataBuilder setCopyRight(String copyRight) {
        this.copyRight = copyRight;
        return this;
    }

    public SongMetaDataBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SongMetaDataBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public SongMetaDataBuilder setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public SongMetaDataBuilder setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public SongMetaDataBuilder setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
        return this;
    }

    public SongMetaDataBuilder setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public SongMetaData createSongMetaData() {
        return new SongMetaData(copyRight, title, artist, recordLabel, duration, uploadFileName, fileLocation);
    }
}