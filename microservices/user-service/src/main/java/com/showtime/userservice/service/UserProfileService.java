package com.showtime.userservice.service;

import com.showtime.userservice.bean.request.UserProfileRequest;
import com.showtime.userservice.domain.UserProfile;

import java.util.List;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
public interface UserProfileService {

    /**
     * To Create User Profile with additional information
     *
     * @param userProfileRequest
     * @return saved UserProfile Info
     */
    public UserProfile createUserProfile(UserProfileRequest userProfileRequest);

    /**
     * To Fetch the User Profile based on the user-id
     *
     * @param userId
     * @return Fetched User Profile
     */
    public UserProfile fetchUserProfile(UUID userId);

    /**
     * To Fetch all User Profiles
     *
     * @return Fetched User Profile
     */
    public List<UserProfile> fetchUserProfiles();

    public void updateUserProfile();

    /**
     * To delete the user profile based on UserId
     *
     * @param userId
     */
    public void deleteUserProfile(UUID userId);

}
