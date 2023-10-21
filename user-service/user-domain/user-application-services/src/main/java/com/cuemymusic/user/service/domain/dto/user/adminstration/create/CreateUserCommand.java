package com.cuemymusic.user.service.domain.dto.user.adminstration.create;


import jakarta.validation.constraints.NotNull;

public record CreateUserCommand(String firstName, String lastName,@NotNull String email) {
}
