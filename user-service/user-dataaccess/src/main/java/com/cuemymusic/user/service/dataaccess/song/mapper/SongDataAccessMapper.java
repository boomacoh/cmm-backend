package com.cuemymusic.user.service.dataaccess.song.mapper;

import com.cuemymusic.user.service.dataaccess.song.entity.UserSongEntity;
import com.cuemymusic.user.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.user.service.domain.entity.Song;
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
                .setIsFavorite(songEntity.getIsFavorite())
                .createSong();
    }
}
