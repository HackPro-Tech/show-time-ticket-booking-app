package com.showtime.userservice.dao.impl;

import com.showtime.corelib.constant.message.Messages;
import com.showtime.exception.UserAlreadyExistsException;
import com.showtime.exception.PersistentException;
import com.showtime.userservice.dao.UserProfileDao;
import com.showtime.userservice.domain.UserProfile;
import com.showtime.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class UserProfileDaoImpl implements UserProfileDao {

    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserProfile createUserProfile(UserProfile userProfile) {
        UserProfile newUserProfile = new UserProfile();
        try {
            Optional<UserProfile> existUserProfile = readUserProfile(userProfile.getUserId());
            if (existUserProfile.isPresent()) {
                log.debug("######### User Profile Already Exist!!!");
                throw new UserAlreadyExistsException(Messages.USER_ALREADY_EXISTS);
            } else {
                userProfile.setUserId(userProfile.getUserId());
                LocalDateTime ldt = LocalDateTime.now();
                ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
                userProfile.setCreatedAt(Date.from(ldtZoned.toInstant()));
                userProfile.setModifiedAt(Date.from(ldtZoned.toInstant()));
                userProfile.setProfileImage(userProfile.getProfileImage());
                newUserProfile = userProfileRepository.save(userProfile);
            }
            return newUserProfile;
        } catch (Exception ex) {
            log.debug("##### Error While saving User Profile in method -> createUserProfile()", ex);
            throw new PersistentException("Error while saving User Profile");
        }
    }

    @Override
    public Optional<UserProfile> readUserProfile(UUID userId) {
        Optional<UserProfile> userProfile = Optional.empty();
        try {
            userProfile = userProfileRepository.findByUserId(userId);
        } catch (Exception ex) {
            log.debug("##### Error While Fetching User Profile in method -> readUserProfile()", ex);
            throw new PersistentException("Error while fetching User Profile");
        }
        return userProfile;
    }

    @Override
    public List<UserProfile> readUserProfiles() {
        List<UserProfile> userProfiles = null;
        try {
            userProfiles = userProfileRepository.findAll();
        } catch (Exception ex) {
            log.debug("##### Error While Fetching All User Profiles in method -> readUserProfiles()", ex);
            throw new PersistentException("Error while fetching all User Profiles");
        }
        return userProfiles;
    }

    @Override
    public void updateUserProfile() {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteUserProfile(UUID userId) {
        try {
            userProfileRepository.deleteByUserId(userId);
        } catch (Exception ex) {
            log.debug("##### Error While Deleting User Profile in method -> deleteUserProfile()", ex);
            throw new PersistentException("Error while deleting user profile");
        }
    }

}
