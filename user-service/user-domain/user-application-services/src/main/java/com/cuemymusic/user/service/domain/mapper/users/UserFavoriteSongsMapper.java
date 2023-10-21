package com.cuemymusic.user.service.domain.mapper.users;

import com.cuemymusic.user.service.domain.dto.user.favorite.clear.ClearFavoriteResponse;
import com.cuemymusic.user.service.domain.dto.user.favorite.set.SetFavoriteResponse;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.Song;
import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@Slf4j
public class UserFavoriteSongsMapper {



//    public ClearFavoriteResponse songToClearFavoriteResponse(User user, Song song){
//        return new ClearFavoriteResponse(user.getId().getValue(),song.getId().getValue(), ZonedDateTime.now());
//    }
//
//    public SetFavoriteResponse songToSetFavoriteResponse(User user, Song song) {
//        return new SetFavoriteResponse(user.getId().getValue(),song.getId().getValue(), ZonedDateTime.now());
//    }
}
