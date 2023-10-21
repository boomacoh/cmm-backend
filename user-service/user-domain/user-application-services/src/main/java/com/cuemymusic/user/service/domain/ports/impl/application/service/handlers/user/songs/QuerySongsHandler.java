package com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.user.songs;

import com.cuemymusic.user.service.domain.dto.song.query.QuerySongCommand;
import com.cuemymusic.user.service.domain.dto.song.query.QuerySongResponse;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.exception.UserDomainException;
import com.cuemymusic.user.service.domain.mapper.SongDataMapper;
import com.cuemymusic.user.service.domain.ports.output.authentication.AuthenticationService;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import com.cuemymusic.user.service.domain.ports.output.repository.song.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class QuerySongsHandler {
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final SongDataMapper songDataMapper;
    private final AuthenticationService authenticationService;

    public QuerySongsHandler(UserRepository userRepository,
                             SongRepository songRepository,
                             SongDataMapper songDataMapper,
                             AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.songDataMapper = songDataMapper;
        this.authenticationService = authenticationService;
    }
    public QuerySongResponse querySongs(
        String token,
        Boolean isOwned,
        Boolean isFavorite
    ) {
        String userEmail = authenticationService.extractUsername(token);
        checkUser(userEmail);
        User user = userRepository.findByEmail(userEmail);
        List<Song> songs;
        if(isFavorite){
            songs = songRepository.findFavoriteMusic(user.getId().getValue());
        }else{
            if(isOwned){
                songs = songRepository.findOwnedMusic(user.getId().getValue());
            }else{
                songs = songRepository.findSharedMusic(user.getId().getValue());
            }
        }
        return songDataMapper.songsToQuerySongResponse(songs);
    }
    private void checkUser(String email) {
        if(!userRepository.isPresentByEmail(email)){
            log.warn("User with EMAIL: " + email + "already exists");
            throw new UserDomainException("User with EMAIL: " + email + "already exists");
        }
    }


}
