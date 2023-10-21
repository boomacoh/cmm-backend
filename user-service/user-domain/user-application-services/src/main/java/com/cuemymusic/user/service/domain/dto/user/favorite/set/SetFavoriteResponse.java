package com.cuemymusic.user.service.domain.dto.user.favorite.set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record SetFavoriteResponse(Boolean isFavorite){}
