package com.cuemymusic.user.service.domain.valueobject;


public class SongMetaData {
    private String copyRight;
    private String title;
    private String artist;
    private String recordLabel;
    private Integer duration;
    private String uploadFileName;
    private String fileLocation;


    public SongMetaData(String copyRight, String title, String artist, String recordLabel, Integer duration, String uploadFileName, String fileLocation) {
        this.copyRight = copyRight;
        this.title = title;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.duration = duration;
        this.uploadFileName = uploadFileName;
        this.fileLocation = fileLocation;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
