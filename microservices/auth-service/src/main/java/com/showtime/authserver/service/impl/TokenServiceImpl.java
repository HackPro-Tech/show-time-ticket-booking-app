package com.showtime.authserver.service.impl;

import com.showtime.authserver.domain.AccessToken;
import com.showtime.authserver.domain.RefreshToken;
import com.showtime.authserver.repository.AccessTokenRepository;
import com.showtime.authserver.repository.RefreshTokenRepository;
import com.showtime.authserver.service.TokenService;
import com.showtime.exception.IAMAuthenticationException;
import com.showtime.exception.IAMServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    public void revokeToken(String tokenId) {
        try {
            boolean isValidToken = true;
            Optional<AccessToken> accessToken = accessTokenRepository.findByTokenId(tokenId);
            if (accessToken.isPresent()) {
                Optional<RefreshToken> refreshToken = refreshTokenRepository.findByTokenId(accessToken.get().getRefreshToken());
                if (refreshToken.isPresent()) {
                    accessTokenRepository.delete(accessToken.get());
                    refreshTokenRepository.delete(refreshToken.get());
                } else {
                    isValidToken = false;
                }
            } else {
                isValidToken = false;
            }

            if (!isValidToken) {
                throw new IAMAuthenticationException("Invalid Token");
            }
        } catch (IAMAuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            log.debug("##### Error while removing the token details in -> revokeToken() ", ex);
            throw new IAMServiceException("Error while revoke the token details");
        }
    }

    @Override
    public Optional<AccessToken> revokeAccessToken(String tokenId) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByTokenId(tokenId);
        if (accessToken.isPresent()) {
            accessTokenRepository.delete(accessToken.get());
        }
        return accessToken;
    }

    @Override
    public Optional<RefreshToken> revokeRefreshToken(String tokenId) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByTokenId(tokenId);
        if (refreshToken.isPresent()) {
            refreshTokenRepository.delete(refreshToken.get());
        }
        return refreshToken;
    }
}
