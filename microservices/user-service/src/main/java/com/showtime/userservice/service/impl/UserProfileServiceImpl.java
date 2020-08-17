package com.showtime.userservice.service.impl;

import com.showtime.exception.PersistentException;
import com.showtime.exception.ServiceException;
import com.showtime.userservice.bean.request.UserProfileRequest;
import com.showtime.userservice.dao.UserProfileDao;
import com.showtime.userservice.domain.UserProfile;
import com.showtime.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDao userProfileDao;

    private final ModelMapper modelMapper;

    @Override
    public UserProfile createUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = null;
        try {
            userProfile = userProfileDao.createUserProfile(modelMapper.map(userProfileRequest, UserProfile.class));
            if (userProfile == null) {
                log.debug("##### User Profile is not saved properly");
                throw new ServiceException("Error While saving User Profile");
            }
        } catch (PersistentException ex) {
            log.debug("##### Error While saving User Profile in method -> createUserProfile()", ex);
            throw new ServiceException(ex.getMessage());
        }
        return userProfile;
    }

    @Override
    public UserProfile fetchUserProfile(UUID userId) {
        try {
            Optional<UserProfile> userProfile = userProfileDao.readUserProfile(userId);
            return userProfile.isPresent() ? userProfile.get() : null;
        } catch (NumberFormatException ex) {
            log.debug("##### Given User ID is not valid UUID in method -> fetchUserProfile()", ex);
            throw new ServiceException("Invalid User ID!!!");
        } catch (Exception ex) {
            log.debug("##### Error While fetching User Profile in method -> fetchUserProfile()", ex);
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<UserProfile> fetchUserProfiles() {
        return userProfileDao.readUserProfiles();
    }

    @Override
    public void updateUserProfile() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUserProfile(UUID userId) {
        userProfileDao.deleteUserProfile(userId);
    }

}
