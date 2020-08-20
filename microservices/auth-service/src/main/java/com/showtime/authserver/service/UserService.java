package com.showtime.authserver.service;

import com.showtime.authserver.bean.request.UserProfileRequest;
import com.showtime.authserver.bean.request.UserSearchRequest;
import com.showtime.authserver.bean.response.UserResponse;
import com.showtime.authserver.domain.UserDetailsPrincipal;

import java.util.List;

/**
 * @author Vengatesan Nagarajan
 */
public interface UserService {

    UserDetailsPrincipal getUserByEmailOrPhoneNo(String emailOrPhoneNo);

    void registerNewUser(UserProfileRequest userProfileRequest);

    List<UserResponse> fetchUsers(UserSearchRequest userSearchRequest);

}