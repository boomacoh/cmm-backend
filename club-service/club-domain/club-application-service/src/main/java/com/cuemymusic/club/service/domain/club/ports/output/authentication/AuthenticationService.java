package com.cuemymusic.club.service.domain.club.ports.output.authentication;

//import com.cuemymusic.user.service.domain.entity.Token;
//import com.cuemymusic.user.service.domain.entity.User;

public interface AuthenticationService {
//    String hashPassword(String password);
//    Token getRefreshToken(User user);
//    Token getToken(User user);
//    void authenticate(String username,String password);
    String extractUsername(String token);

//    boolean isTokenValid(String refreshToken, User user);
//    void sendResetPasswordEmail(String token,User user);


}
