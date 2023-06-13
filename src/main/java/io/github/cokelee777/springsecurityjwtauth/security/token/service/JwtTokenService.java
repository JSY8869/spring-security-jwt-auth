package io.github.cokelee777.springsecurityjwtauth.security.token.service;

import io.github.cokelee777.springsecurityjwtauth.security.auth.JwtUserDetails;
import io.github.cokelee777.springsecurityjwtauth.security.token.domain.JwtAccessToken;
import io.github.cokelee777.springsecurityjwtauth.security.token.domain.JwtRefreshToken;
import io.github.cokelee777.springsecurityjwtauth.security.token.extractor.JwtTokenExtractor;
import io.github.cokelee777.springsecurityjwtauth.security.token.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService implements JwtAccessTokenService, JwtRefreshTokenService, JwtTokenDecryptService {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenExtractor jwtTokenExtractor;

    public JwtTokenService(JwtTokenProvider jwtMemoryTokenProvider,
                           JwtTokenExtractor jwtMemoryTokenExtractor) {
        this.jwtTokenProvider = jwtMemoryTokenProvider;
        this.jwtTokenExtractor = jwtMemoryTokenExtractor;
    }

    @Override
    public JwtAccessToken issueAccessToken(JwtUserDetails jwtUserDetails) {
        return jwtTokenProvider.getAccessToken(jwtUserDetails);
    }

    @Override
    public JwtRefreshToken issueRefreshToken(JwtUserDetails jwtUserDetails) {
        return jwtTokenProvider.getRefreshToken(jwtUserDetails);
    }

    @Override
    public JwtUserDetails decryptAccessToken(String accessToken) {
        return jwtTokenExtractor.extractAccessToken(accessToken);
    }

    @Override
    public JwtUserDetails decryptRefreshToken(String refreshToken) {
        return jwtTokenExtractor.extractRefreshToken(refreshToken);
    }
}
