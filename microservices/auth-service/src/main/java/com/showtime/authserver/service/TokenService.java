package com.showtime.authserver.service;

import com.showtime.authserver.domain.AccessToken;
import com.showtime.authserver.domain.RefreshToken;

import java.util.Optional;

/**
 * @author Vengatesan Nagarajan
 */
public interface TokenService {

    // Revoke Access and Refresh Token

    /**
     * Revoke both Access and Refresh Token
     *
     * @param tokenId
     */
    void revokeToken(String tokenId);

    /**
     * Revoke Access Token
     *
     * @param tokenId
     * @return
     */
    Optional<AccessToken> revokeAccessToken(String tokenId);

    /**
     * Revoke Refresh Token
     *
     * @param tokenId
     * @return
     */
    Optional<RefreshToken> revokeRefreshToken(String tokenId);

}
