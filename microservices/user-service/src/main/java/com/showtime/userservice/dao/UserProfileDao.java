package com.showtime.userservice.dao;

import com.showtime.userservice.domain.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
public interface UserProfileDao {

    /**
     * To create new user profile based on user id of IAM
     *
     * @param userProfile
     * @return Saved UserProfile info
     */
    public UserProfile createUserProfile(UserProfile userProfile);

    /**
     * To fetch the user profile based on user id
     *
     * @param userId
     * @return fetched User Profile
     */
    public Optional<UserProfile> readUserProfile(UUID userId);

    /**
     * To fetch the all user profiles
     *
     * @return fetched User Profiles
     */
    public List<UserProfile> readUserProfiles();


    public void updateUserProfile();

    /**
     * To Delete the User Profile based on UserId
     *
     * @param userId
     */
    public void deleteUserProfile(UUID userId);
}
