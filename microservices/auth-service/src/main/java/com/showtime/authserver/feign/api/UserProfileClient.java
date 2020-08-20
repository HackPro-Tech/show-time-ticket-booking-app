package com.showtime.authserver.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.showtime.authserver.bean.request.UserProfileRequest;
import com.showtime.authserver.feign.fallbacks.UserProfileClientFallBack;

import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */

@FeignClient(name = "user-service", url = "http://localhost:8100/user", fallback = UserProfileClientFallBack.class)
public interface UserProfileClient {

    /**
     * To Register new user profiles
     *
     * @param userId
     * @param userInfoRequest
     */
    @PostMapping("/create-profile/{userId}")
    public void createUserProfileDetails(@PathVariable("userId") UUID userId, @RequestBody UserProfileRequest userInfoRequest);

}
