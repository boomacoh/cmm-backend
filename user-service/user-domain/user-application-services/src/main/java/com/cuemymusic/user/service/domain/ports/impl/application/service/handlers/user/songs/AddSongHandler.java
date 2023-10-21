package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs;

import com.cuemymusic.user.service.domain.dto.song.add.AddSongResponse;
import com.cuemymusic.user.service.domain.entity.builder.SongBuilder;
import com.cuemymusic.user.service.domain.event.SongAddedEvent;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
public class AddSongHandler {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final SongRepository songRepository;
    private final SongDataMapper songDataMapper;
    private final UserDomainService userDomainService;

    public AddSongHandler(
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
    public AddSongResponse addSong(String token, String name, SongMetaData songMetaData) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        Song song = new SongBuilder()
                .setSongId(new SongId(UUID.randomUUID()))
                .setName(name)
                .setOwner(user.getId().getValue())
                .setTitle(songMetaData.getTitle())
                .setUploadName(songMetaData.getUploadFileName())
                .setDuration(songMetaData.getDuration())
                .setCopyRight(songMetaData.getCopyRight())
                .setFileLocation(songMetaData.getFileLocation())
                .setRecordLabel(songMetaData.getRecordLabel())
                .setArtist(songMetaData.getArtist())
                .createSong();
        log.warn("Will Add Song To User  => " + user.getSongs().toString());
        log.warn(song.toString());
        user.addSong(song);
        log.warn("song ADDED");
        songRepository.save(song);
        userRepository.save(user);
//        SongAddedEvent event =  userDomainService.addSong(user,song);
        AddSongResponse res = songDataMapper.songToAddSongResponse(user,song);
        return res;
    }

    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }

}
