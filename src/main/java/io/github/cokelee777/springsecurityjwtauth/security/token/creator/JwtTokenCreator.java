package io.github.cokelee777.springsecurityjwtauth.security.token.creator;

import io.github.cokelee777.springsecurityjwtauth.security.auth.JwtUserDetails;

public interface JwtTokenCreator<T extends JwtUserDetails> extends TokenCreator {

    //TODO: Need Refactoring!
    String createAccessToken(T userDetails);
    String createRefreshToken(T userDetails);
}
