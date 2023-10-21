package com.cuemymusic.user.service.dataaccess.song.adaptor;


import com.cuemymusic.user.service.dataaccess.song.entity.SongEntity;
import com.cuemymusic.user.service.dataaccess.song.entity.UserSongEntity;
import com.cuemymusic.user.service.dataaccess.song.mapper.SongDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.song.mapper.UserSongDataAccessMapper;
import com.cuemymusic.user.service.dataaccess.song.repository.SongJpaRepository;
import com.cuemymusic.user.service.dataaccess.song.repository.UserSongJpaRepository;
import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import com.cuemymusic.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SongRepositoryImpl implements SongRepository {
    private final SongJpaRepository songJpaRepository;
    private final UserSongJpaRepository userSongJpaRepository;
    private final UserSongDataAccessMapper userSongDataAccessMapper;
    private final SongDataAccessMapper songDataAccessMapper;

    public SongRepositoryImpl(SongJpaRepository songJpaRepository,
                              UserSongJpaRepository userSongJpaRepository,
                              UserSongDataAccessMapper userSongDataAccessMapper,
                              SongDataAccessMapper songDataAccessMapper) {
        this.songJpaRepository = songJpaRepository;
        this.userSongJpaRepository = userSongJpaRepository;
        this.userSongDataAccessMapper = userSongDataAccessMapper;
        this.songDataAccessMapper = songDataAccessMapper;
    }



    @Override
    public Song save(Song song) {
        UserSongEntity userSongEntity =   userSongDataAccessMapper.songToSongEntity(song);
        return userSongDataAccessMapper.songEntityToSong(
                userSongJpaRepository.save(userSongEntity)
        );
    }

    @Override
    public Optional<Song> findById(UUID songId, UUID userId) {
        return songJpaRepository.findSongById(songId,userId).map(
                song -> songDataAccessMapper.songEntityToSong(
                        song
                )
        );
    }

    @Override
    public Optional<Song> findById(UUID songId) {
        return userSongJpaRepository.findById(songId).map(
                song -> userSongDataAccessMapper.songEntityToSong(
                        song
                )
        );
    }



    @Override
    public List<Song> findAll() {
        return userSongJpaRepository.findAll().stream().map(
                song -> userSongDataAccessMapper.songEntityToSong(
                    song
                )
        ).toList();
    }

    @Override
    public List<Song> findOwnedMusic(UUID userId) {
        List<SongEntity> songEntityList =  songJpaRepository.findOwnedMusic(userId);
        return songEntityList.stream().map(songDataAccessMapper::songEntityToSong).toList();
    }

    @Override
    public List<Song> findSharedMusic(UUID userId) {
        List<SongEntity> songEntityList =  songJpaRepository.findSharedMusic(userId);
        return songEntityList.stream().map(songDataAccessMapper::songEntityToSong).toList();
    }

    @Override
    public List<Song> findFavoriteMusic(UUID userId) {
        List<SongEntity> songEntityList =  songJpaRepository.findFavoriteMusic(userId);
        return songEntityList.stream().map(songDataAccessMapper::songEntityToSong).toList();    }

    @Override
    public void clearFavorite(UUID userId, UUID songId) {
        songJpaRepository.clearFavorite(userId,songId);
    }

    @Override
    public void setFavorite(UUID userId, UUID songId) {
        songJpaRepository.setFavorite(userId,songId);
    }


    @Override
    public void deleteById(UUID songId) {
        userSongJpaRepository.deleteById(songId);
    }

    @Override
    public void revokeAllConnections(UUID songId) {
        userSongJpaRepository.revokeAllShares(songId);
        userSongJpaRepository.revokeAllFavorites(songId);
    }

}
