package com.cuemymusic.user.service.domain.dto.authentication.password.reset;

import jakarta.validation.constraints.NotNull;

public record ResetPasswordCommand(@NotNull String email) {
}
