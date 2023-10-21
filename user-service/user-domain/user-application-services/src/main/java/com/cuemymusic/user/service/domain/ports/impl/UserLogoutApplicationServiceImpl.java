package com.cuemymusic.user.service.domain.ports.impl;

import com.cuemymusic.user.service.domain.ports.impl.application.service.handlers.authentication.LogoutHandler;
import com.cuemymusic.user.service.domain.ports.input.service.UserLogoutApplicationService;
import org.springframework.stereotype.Component;


@Component
public class UserLogoutApplicationServiceImpl implements UserLogoutApplicationService {

    final private LogoutHandler logoutHandler;

    public UserLogoutApplicationServiceImpl(LogoutHandler logoutHandler) {

        this.logoutHandler = logoutHandler;
    }

    @Override
    public void logout(String token) {
      logoutHandler.logout(token);
    }

}
