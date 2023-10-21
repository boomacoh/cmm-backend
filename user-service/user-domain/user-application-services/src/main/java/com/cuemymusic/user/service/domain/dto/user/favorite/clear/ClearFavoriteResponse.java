package com.cuemymusic.user.service.domain.dto.user.favorite.clear;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;
@Builder
public record ClearFavoriteResponse(Boolean isFavorite) {}
