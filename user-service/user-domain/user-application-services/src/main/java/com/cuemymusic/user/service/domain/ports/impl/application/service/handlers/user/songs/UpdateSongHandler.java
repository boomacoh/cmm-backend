package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs;

import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.dto.song.add.AddSongResponse;
import com.cuemymusic.user.service.domain.dto.song.update.UpdateName;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UpdateSongHandler {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final SongRepository songRepository;
    private final SongDataMapper songDataMapper;
    private final UserDomainService userDomainService;

    public UpdateSongHandler(
            UserRepository userRepository,
            SongRepository songRepository,
            SongDataMapper songDataMapper,
            UserDomainService userDomainService, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.songDataMapper = songDataMapper;
        this.userDomainService = userDomainService;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public void updateName(String token, UUID songId, UpdateName updateName) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        final User user = userRepository.findByEmail(userEmail);
        final Optional<Song> optionalSong = songRepository.findById(songId);
        if(optionalSong.isEmpty()){
            throw new UserDomainException("Song doesn't exist");
        }
        final Song song = optionalSong.get();
        if(song.getOwner().equals( user.getId().getValue())) {
            song.setName(updateName.name());
            songRepository.save(song);
        }else{
            throw new UserDomainException("User is not authorized");
        }
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
