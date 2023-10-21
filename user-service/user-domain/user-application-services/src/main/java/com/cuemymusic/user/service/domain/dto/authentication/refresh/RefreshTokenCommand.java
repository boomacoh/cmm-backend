package com.cuemymusic.user.service.domain.dto.authentication.refresh;

public class RefreshTokenCommand {
    private String accessToken;

    public RefreshTokenCommand(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
