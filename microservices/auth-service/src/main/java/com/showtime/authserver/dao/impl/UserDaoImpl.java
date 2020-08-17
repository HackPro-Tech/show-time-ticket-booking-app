package com.showtime.authserver.dao.impl;

import com.showtime.authserver.dao.UserDao;
import com.showtime.authserver.domain.User;
import com.showtime.authserver.repository.UserRepository;
import com.showtime.exception.IAMPersistentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    /**
     * To fetch user based on Email Or Phone No
     */
    @Override
    public Optional<User> findByEmailOrPhoneNo(String emailOrPhoneNo) {
        Optional<User> user = userRepository.findByEmail(emailOrPhoneNo);
        if (!user.isPresent()) {
            user = userRepository.findByPhoneNo(emailOrPhoneNo);
        }
        return user;
    }

    /**
     * To Save New User Details or Register New User
     */
    @Override
    public User saveUserDetails(User user) {
        User registeredUserDetails = null;
        try {
            registeredUserDetails = userRepository.save(user);
        } catch (CassandraConnectionFailureException failureEx) {
            log.debug("##### Error while connecting to Cassandra Database");
            throw failureEx;
        } catch (Exception ex) {
            log.debug("##### Error while saving new user details");
            throw new IAMPersistentException("Error while saving new user");
        }
        return registeredUserDetails;
    }

    /**
     * To fetch All Users Information Based on Pagination criteria
     */
    @Override
    public List<User> getAllUsers(int reqRecordCount, int reqPageNo) {
        List<User> userInfo = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, reqRecordCount);
            Slice<User> slice = null;
            Slice<User> exactResult = null;
            int initialPageCounter = 0;
            while (initialPageCounter <= reqPageNo) {
                slice = userRepository.findAll(pageable);
                if (!slice.hasNext()) {
                    break;
                } else {
                    exactResult = slice;
                }
                pageable = slice.nextPageable();
                initialPageCounter++;
            }
            if (exactResult != null) {
                userInfo = exactResult.getContent();
            } else if (slice != null) {
                userInfo = slice.getContent();
            }
        } catch (Exception ex) {
            log.debug("##### Error while fecthing users info in method => getAllUsers() ", ex);
            throw new IAMPersistentException("Error while fetching all users info");
        }
        return userInfo;
    }

    /**
     * To Remove the user's information
     *
     * @param userId
     */
    @Override
    public void removeUserIdentityDetails(UUID userId) {
        userRepository.deleteByUserId(userId);
    }

}