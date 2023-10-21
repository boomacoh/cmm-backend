package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs;


import com.cuemymusic.user.service.domain.dto.song.remove.RemoveSongResponse;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class RemoveSongHandler {
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final UserDomainService userDomainService;
    private final SongDataMapper songDataMapper;
    private final AuthenticationService authenticationService;

    public RemoveSongHandler(
            UserRepository userRepository,
            SongRepository songRepository,
            UserDomainService userDomainService,
            SongDataMapper songDataMapper,
            AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.userDomainService = userDomainService;
        this.songDataMapper = songDataMapper;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public RemoveSongResponse removeSong(String token, UUID songId) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        checkSong(songId,user.getId().getValue());
        Song song = songRepository.findById(songId).get();
        user.removeSong(new SongId(songId));
        userRepository.save(user);
        if(user.getId().getValue().equals(song.getOwner()) )
        {
            songRepository.revokeAllConnections(songId);
            songRepository.deleteById(songId);
        }
        return RemoveSongResponse.builder()
                .deletedSong(songDataMapper.songToSongData(song))
                .build();
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }
    private void checkSong(UUID songId,UUID userId) {
        Optional<Song> song = songRepository.findById(songId,userId);
        if(song.isEmpty()){
            log.warn("Song with ID: " + songId + " Is NOT FOUND");
            throw new UserDomainException("Song with ID: " + songId + " Is NOT FOUND");
        }
    }

}
