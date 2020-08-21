package com.showtime.authserver.controller;

import com.showtime.authserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    // Revoke Access & Refresh Token
    @DeleteMapping(value = "/revoke/{tokenId}")
    public void revokeToken(@PathVariable("tokenId") String tokenId) {
        tokenService.revokeToken(tokenId);
    }

    // Remove Access Token & Refresh Token when application restart
}
