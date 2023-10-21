package com.cuemymusic.club.service.domain.club.dto.playlist.generateQR;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GetQRCommand(
        UUID deviceId
) {
}
