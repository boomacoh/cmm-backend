package com.cuemymusic.club.service.domain.club.dto.playlist.swapSongs;

import jakarta.validation.Valid;

import java.util.UUID;

public record SwapSongsCommand(@Valid UUID song1, @Valid UUID song2) {
}
