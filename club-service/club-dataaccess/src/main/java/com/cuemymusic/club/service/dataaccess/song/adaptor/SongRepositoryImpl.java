package com.cuemymusic.club.service.dataaccess.song.adaptor;


import com.cuemymusic.club.service.dataaccess.song.mapper.SongDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.song.repository.SongJpaRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.song.SongRepository;
import com.cuemymusic.club.service.domain.entity.Song;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SongRepositoryImpl implements SongRepository {
    private final SongJpaRepository songJpaRepository;
    private final SongDataAccessMapper songDataAccessMapper;

    public SongRepositoryImpl(SongJpaRepository songJpaRepository,
                              SongDataAccessMapper songDataAccessMapper) {
        this.songJpaRepository = songJpaRepository;
        this.songDataAccessMapper = songDataAccessMapper;
    }


    @Override
    public Optional<Song> find(UUID userId, UUID songId) {
        return songJpaRepository.findSongById(userId,songId).map(
                song -> songDataAccessMapper.songEntityToSong(
                        song
                )
        );
    }

}
