package com.cuemymusic.user.service.domain.dto.authentication.password.update;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordCommand(@NotNull String password) {
}
