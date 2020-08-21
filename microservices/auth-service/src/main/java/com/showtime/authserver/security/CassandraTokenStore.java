package com.showtime.authserver.security;

import com.showtime.authserver.domain.AccessToken;
import com.showtime.authserver.domain.RefreshToken;
import com.showtime.authserver.repository.AccessTokenRepository;
import com.showtime.authserver.repository.RefreshTokenRepository;
import com.showtime.authserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;

/**
 * @author Vengatesan Nagarajan
 */
@Service
public class CassandraTokenStore implements TokenStore {

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationKeyGenerator authenticationKeyGenerator;

    @Autowired
    private TokenService tokenService;

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByTokenId(token);
        if (accessToken.isPresent()) {
            try {
                return (OAuth2Authentication) deserializeTokenOrAuthentication(accessToken.get().getAuthentication());
            } catch (IllegalArgumentException e) {
                tokenService.revokeAccessToken(token);
            }
        }
        return null;
    }

    // Store Access Token, remove Existing Token and store Refresh token
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        ByteBuffer bufferedToken = ByteBuffer.wrap(SerializationUtils.serialize(token));
        ByteBuffer bufferedAuthentication = ByteBuffer.wrap(SerializationUtils.serialize(authentication));
        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        // Remove existing Refresh Token
        if (readAccessToken(token.getValue()) != null) {
            tokenService.revokeAccessToken(token.getValue());
        }

        // Store new Access Token
        AccessToken accessToken = new AccessToken();
        accessToken.setTokenId(token.getValue());
        accessToken.setToken(bufferedToken);
        accessToken.setAuthentication(bufferedAuthentication);
        accessToken.setRefreshToken(refreshToken);
        accessToken.setClientId(authentication.getOAuth2Request().getClientId());
        accessToken.setUsername(authentication.isClientOnly() ? null : authentication.getName());
        accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        accessTokenRepository.save(accessToken);

        // Store new Refresh Token
        if (token.getRefreshToken() != null && token.getRefreshToken().getValue() != null) {
            storeRefreshToken(token.getRefreshToken(), authentication);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByTokenId(tokenId);
        if (accessToken.isPresent()) {
            try {
                return (OAuth2AccessToken) deserializeTokenOrAuthentication(accessToken.get().getToken());
            } catch (IllegalArgumentException ex) {
                tokenService.revokeAccessToken(tokenId);
            }
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        tokenService.revokeAccessToken(token.getValue());
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        ByteBuffer bufferedRefreshToken = ByteBuffer.wrap(SerializationUtils.serialize(refreshToken));
        ByteBuffer bufferedAuthentication = ByteBuffer.wrap(SerializationUtils.serialize(authentication));
        final String tokenKey = refreshToken.getValue();
        RefreshToken rfToken = new RefreshToken(tokenKey, bufferedRefreshToken, bufferedAuthentication);
        refreshTokenRepository.save(rfToken);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenId) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByTokenId(tokenId);
        if (refreshToken.isPresent()) {
            try {
                return (OAuth2RefreshToken) deserializeTokenOrAuthentication(refreshToken.get().getToken());
            } catch (IllegalArgumentException e) {
                tokenService.revokeRefreshToken(tokenId);
            }
        }
        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByTokenId(token.getValue());
        if (refreshToken.isPresent()) {
            try {
                return (OAuth2Authentication) deserializeTokenOrAuthentication(refreshToken.get().getAuthentication());
            } catch (IllegalArgumentException e) {
                tokenService.revokeRefreshToken(token.getValue());
            }
        }
        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        tokenService.revokeRefreshToken(token.getValue());
    }

    // Remove Access Token Using Refresh Token
    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        Optional<AccessToken> accessToken = accessTokenRepository.findByRefreshToken(refreshToken.getValue());
        if (accessToken.isPresent()) {
            accessTokenRepository.delete(accessToken.get());
        }
    }

    // Get Access Token based on Authetication ID
    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        Optional<AccessToken> accessToken = accessTokenRepository.findByAuthenticationId(authenticationId);
        if (accessToken.isPresent()) {
            return (OAuth2AccessToken) deserializeTokenOrAuthentication(accessToken.get().getToken());
        }
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        final List<AccessToken> oAuth2AccessTokens = accessTokenRepository.findByClientIdAndUsername(userName,
                clientId);
        final Collection<AccessToken> noNullTokens = filter(oAuth2AccessTokens, Objects::nonNull);
        return transform(noNullTokens, token -> (OAuth2AccessToken) deserializeTokenOrAuthentication(token.getToken()));
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        final List<AccessToken> oAuth2AccessTokens = accessTokenRepository.findByClientId(clientId);
        final Collection<AccessToken> noNullTokens = filter(oAuth2AccessTokens, Objects::nonNull);
        return transform(noNullTokens, token -> (OAuth2AccessToken) deserializeTokenOrAuthentication(token.getToken()));
    }

    // Deserialize Token Or Authentication Info
    protected Object deserializeTokenOrAuthentication(ByteBuffer tokenOrAuthentication) {
        byte[] bytes = new byte[tokenOrAuthentication.remaining()];
        tokenOrAuthentication.get(bytes);
        return SerializationUtils.deserialize(bytes);
    }

}
