package com.cuemymusic.user.service.domain.dto.authentication.password.change;

public record ChangePasswordCommand(String currentPassword, String newPassword) {
}
