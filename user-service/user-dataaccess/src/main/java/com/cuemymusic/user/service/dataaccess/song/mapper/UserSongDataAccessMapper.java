package com.cuemymusic.user.service.dataaccess.song.mapper;

import com.cuemymusic.user.service.dataaccess.song.entity.UserSongEntity;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import org.springframework.stereotype.Component;

@Component
public class UserSongDataAccessMapper {
    public UserSongEntity songToSongEntity(Song song){
        return UserSongEntity.builder()
                .id(song.getId().getValue())
                .name(song.getName())
                .artist(song.getArtist())
                .fileLocation(song.getFileLocation())
                .uploadName(song.getUploadName())
                .copyRight(song.getCopyRight())
                .recordLabel(song.getRecordLabel())
                .title(song.getTitle())
                .duration(song.getDuration())
                .ownerId(song.getOwner())
                .build();
    }

    public Song songEntityToSong(UserSongEntity songEntity){
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
                .setOwner(songEntity.getOwnerId())
                .createSong();
    }
}
