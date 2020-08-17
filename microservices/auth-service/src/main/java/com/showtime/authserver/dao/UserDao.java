package com.showtime.authserver.dao;

import com.showtime.authserver.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
public interface UserDao {

    /**
     * To fetch user based on Email Or Phone No
     *
     * @param emailOrPhoneNo
     * @return Matched User
     */
    public Optional<User> findByEmailOrPhoneNo(String emailOrPhoneNo);

    /**
     * To Save New User Details or Register New User
     *
     * @param user
     * @return registered user details
     */
    public User saveUserDetails(User user);

    /**
     * To fetch All Users Information Based on Pagination criteria
     *
     * @param reqRecordCount
     * @param reqPageNo
     * @return List Of Users
     */
    public List<User> getAllUsers(int reqRecordCount, int reqPageNo);

    /**
     * To Remove the existing User based on USER ID
     *
     * @param userId
     */
    public void removeUserIdentityDetails(UUID userId);

}