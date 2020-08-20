package com.showtime.authserver.feign.fallbacks;

import org.springframework.stereotype.Component;

import com.showtime.authserver.bean.request.UserProfileRequest;
import com.showtime.authserver.dao.UserDao;
import com.showtime.authserver.feign.api.UserProfileClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserProfileClientFallBack implements UserProfileClient {

    private final UserDao userDao;

    /**
     * To Revoke the already saved User Identity Details
     */
    @Override
    public void createUserProfileDetails(UUID userId, UserProfileRequest userProfileRequest) {
        log.debug("####### Fallback method gets initiated for Register New User");
        userDao.removeUserIdentityDetails(userId);
    }

}
