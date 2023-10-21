package com.cuemymusic.club.service.domain.club.mapper.playlist;


import com.cuemymusic.club.service.domain.club.dto.common.UserDTO;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistDTO;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.PlaylistSongData;
import com.cuemymusic.club.service.domain.club.dto.playlist.common.SongData;
import com.cuemymusic.club.service.domain.entity.PlaylistSong;
import com.cuemymusic.club.service.domain.entity.Song;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.club.service.domain.entity.builder.PlaylistSongBuilder;
import com.cuemymusic.club.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.club.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistDataMapper {
    public UserDTO userToUserDto(User user){
        return UserDTO.builder()
                .id(user.getUserId().getValue())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
    public User userDtoToUser(UserDTO user){
        return new UserBuilder()
                .setUserId(new UserId(user.id()))
                .setEmail(user.email())
                .setFirstName(user.firstName())
                .setLastName(user.lastName())
                .createUser();
    }

    public SongData songToSongData(Song song){
        return SongData.builder()
                .id(song.getId().getValue())
                .name(song.getName())
                .title(song.getTitle())
                .artist(song.getArtist())
                .fileLocation(song.getFileLocation())
                .copyRight(song.getCopyRight())
                .recordLabel(song.getRecordLabel())
                .duration(song.getDuration())
                .uploadName(song.getUploadName())
                .build();
    }
    public Song songDataToSong(SongData songData){
        return new SongBuilder()
                .setSongId(new SongId(songData.id()))
                .setName(songData.name())
                .setTitle(songData.title())
                .setArtist(songData.artist())
                .setFileLocation(songData.fileLocation())
                .setCopyRight(songData.copyRight())
                .setRecordLabel(songData.recordLabel())
                .setDuration(songData.duration())
                .setUploadName(songData.uploadName())
                .createSong();
    }
    public PlaylistSongData playlistSongToPlaylistSongData(PlaylistSong playlistSong){
        return PlaylistSongData.builder()
                .id(playlistSong.getId().getValue())
                .user(userToUserDto(playlistSong.getUser()))
                .song(songToSongData(playlistSong.getSong()))
                .eta(playlistSong.getEta())
                .played(playlistSong.getPlayed())
                .build();
    }

    public PlaylistSong playlistSongToPlaylistSongData(PlaylistSongData playlistSongData){
        return new PlaylistSongBuilder()
                .setId(new PlaylistSongId(playlistSongData.id()))
                .setUser(userDtoToUser(playlistSongData.user()))
                .setSong(songDataToSong(playlistSongData.song()))
                .setEta(playlistSongData.eta())
                .setPlayed(playlistSongData.played())
                .createPlaylistSong();
    }
    public PlaylistDTO playlistToPlaylistDTO(List<PlaylistSong> playlist){
        return PlaylistDTO.builder()
                .songs(
                        playlist.stream().map(this::playlistSongToPlaylistSongData).toList()
                )
                .build();
    }
}
