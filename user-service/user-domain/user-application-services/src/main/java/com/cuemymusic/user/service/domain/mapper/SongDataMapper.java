package com.cuemymusic.user.service.domain.mapper;

import com.cuemymusic.user.service.domain.dto.song.add.AddSongCommand;
import com.cuemymusic.user.service.domain.dto.song.add.AddSongResponse;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongData;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongResponse;
import com.cuemymusic.user.service.domain.dto.song.remove.RemoveSongResponse;
import com.cuemymusic.user.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class SongDataMapper {
    public Song addSongCommandToSong(AddSongCommand songCommand){

        return new SongBuilder()
                  .createSong();
    }

    public AddSongResponse songToAddSongResponse(User user, Song song) {
        return new AddSongResponse(songToSongData(song));
    }

    public QuerySongResponse songsToQuerySongResponse(List<Song> songs) {
        return new QuerySongResponse(
                songs.stream().map(this::songToSongData).toList()
        );
    }
    public QuerySongData songToSongData(Song song){
        return QuerySongData.builder()
                .id(song.getId().getValue())
                .name(song.getName())
                .title(song.getTitle())
                .copyRight(song.getCopyRight())
                .duration(song.getDuration())
                .fileLocation(song.getFileLocation())
                .uploadName(song.getUploadName())
                .artist(song.getArtist())
                .recordLabel(song.getRecordLabel())
                .isFavorite(song.getFavorite())
                .build();
    }
}
