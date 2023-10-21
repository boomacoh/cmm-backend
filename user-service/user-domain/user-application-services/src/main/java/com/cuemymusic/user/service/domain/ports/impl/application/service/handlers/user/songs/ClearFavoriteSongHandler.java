package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs;

import com.cuemymusic.user.service.domain.dto.user.favorite.clear.ClearFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.user.favorite.set.SetFavoriteResponse;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import com.cuemymusic.user.service.domain.valueobject.SongId;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ClearFavoriteSongHandler {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final SongRepository songRepository;
    private final SongDataMapper songDataMapper;
    private final UserDomainService userDomainService;

    public ClearFavoriteSongHandler(UserRepository userRepository,
                                    AuthenticationService authenticationService,
                                    SongRepository songRepository,
                                    SongDataMapper songDataMapper,
                                    UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.songRepository = songRepository;
        this.songDataMapper = songDataMapper;
        this.userDomainService = userDomainService;
    }

    @Transactional
    public ClearFavoriteResponse clearFavorite(String token, UUID id){
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        checkSong(id,user.getId().getValue());
        songRepository.clearFavorite(user.getId().getValue(),id);
        return  ClearFavoriteResponse.builder().isFavorite(false).build();
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }
    private void checkSong(UUID songId, UUID userId) {
        Optional<Song> song = songRepository.findById(songId, userId);
        if(song.isEmpty()){
            log.warn("Song with ID: " + songId + " Is NOT FOUND");
            throw new UserDomainException("Song with ID: " + songId + " Is NOT FOUND");
        }
    }
}
