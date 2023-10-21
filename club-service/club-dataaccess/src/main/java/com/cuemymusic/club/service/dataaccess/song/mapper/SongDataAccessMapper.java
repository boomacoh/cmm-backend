package com.cuemymusic.club.service.dataaccess.song.mapper;

import com.cuemymusic.club.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.club.service.domain.entity.Song;
import com.cuemymusic.club.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import org.springframework.stereotype.Component;


@Component
public class SongDataAccessMapper {
    public Song songEntityToSong(SongEntity songEntity){
        return new SongBuilder()
                .setSongId(new SongId(songEntity.getId()))
                .setName(songEntity.getName())
                .setFileLocation(songEntity.getFileLocation())
                .setArtist(songEntity.getArtist())
                .setCopyRight(songEntity.getCopyRight())
                .setDuration(songEntity.getDuration())
                .setRecordLabel(songEntity.getRecordLabel())
                .setTitle(songEntity.getTitle())
                .setUploadName(songEntity.getUploadName())
                .createSong();
    }
}
