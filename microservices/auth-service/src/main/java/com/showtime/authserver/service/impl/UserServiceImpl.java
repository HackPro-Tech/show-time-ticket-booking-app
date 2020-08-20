package com.showtime.authserver.service.impl;

import com.showtime.authserver.bean.request.UserProfileRequest;
import com.showtime.authserver.bean.request.UserSearchRequest;
import com.showtime.authserver.bean.response.UserResponse;
import com.showtime.authserver.dao.UserDao;
import com.showtime.authserver.domain.User;
import com.showtime.authserver.domain.UserDetailsPrincipal;
import com.showtime.authserver.feign.api.UserProfileClient;
import com.showtime.authserver.service.UserService;
import com.showtime.corelib.constant.UserRoles;
import com.showtime.corelib.constant.message.Messages;
import com.showtime.corelib.constant.value.ValueConstants;
import com.showtime.exception.IAMServiceException;
import com.showtime.exception.MaxRecordLimitException;
import com.showtime.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final ModelMapper modelMapper;

    private final UserProfileClient userProfileClient;

    /**
     * Environment Variables
     */
    @Value("${records.max-limit}")
    private int maxLimit;

    @Value("${records.initial-count}")
    private int initialCount;

    @Value("${records.page-no}")
    private int pageNo;

    @Override
    public UserDetailsPrincipal getUserByEmailOrPhoneNo(String emailOrPhoneNo) {
        // fetch user based on Email Or Phone no
        Optional<User> user = userDao.findByEmailOrPhoneNo(emailOrPhoneNo);
        UserDetailsPrincipal userDetailsPrincipal = null;
        if (user.isPresent()) {
            userDetailsPrincipal = modelMapper.map(user.get(), UserDetailsPrincipal.class);
        }
        return userDetailsPrincipal;
    }

    /**
     * To Register New User and publish registered notification into kafka
     *
     * @param userProfileRequest
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void registerNewUser(UserProfileRequest userProfileRequest) {
        try {
            if (checkUserAlreadyExists(userProfileRequest)) {
                throw new UserAlreadyExistsException(Messages.USER_ALREADY_EXISTS);
            } else {
                User registeredUserDetails = userDao.saveUserDetails(mapUserRequest(userProfileRequest));

                // Invoke the User Profile Service API
                createUserProfile(registeredUserDetails);
            }
        } catch (UserAlreadyExistsException userException) {
            log.debug("##### Current User is already exists ", userException);
            throw userException;
        } catch (Exception ex) {
            log.debug("##### Error while register new user ", ex);
            throw new IAMServiceException(ex.getMessage());
        }
    }

    private boolean checkUserAlreadyExists(UserProfileRequest userProfileRequest) {
        Optional<User> user = userDao.findByEmailOrPhoneNo(userProfileRequest.getEmail());
        return user.isPresent();
    }

    // Map User Profile Request
    private User mapUserRequest(UserProfileRequest userProfileRequest) {
        User user = new User();
        user.setEmail(userProfileRequest.getEmail());
        user.setPhoneNo(userProfileRequest.getPhoneNo());
        user.setUserId(UUID.randomUUID());
        user.setPassword(userProfileRequest.getPassword());
        user.setEnabled(ValueConstants.TRUE);
        user.setAccountNonExpired(ValueConstants.TRUE);
        user.setAccountNonLocked(ValueConstants.TRUE);
        user.setCredentialsNonExpired(ValueConstants.TRUE);
        user.setRoles(new HashSet<>(Arrays.asList(UserRoles.USER.getRole())));
        return user;
    }

    // Invoke the User Profile Service API
    private void createUserProfile(User user) {
        UserProfileRequest userProfileRequest = new UserProfileRequest();
        userProfileRequest.setEmail(user.getEmail());
        userProfileRequest.setPhoneNo(user.getPhoneNo());
        userProfileClient.createUserProfileDetails(user.getUserId(), userProfileRequest);
    }

    @Override
    public List<UserResponse> fetchUsers(UserSearchRequest userSearchRequest) {
        List<UserResponse> usersResponse = new ArrayList<>();
        try {
            if (maxLimit >= userSearchRequest.getRecordsCount()) {
                int reqRecordCount = userSearchRequest.getRecordsCount() <= 0 ? initialCount
                        : userSearchRequest.getRecordsCount();
                int reqPageNo = userSearchRequest.getPageNo() < 0 ? pageNo : userSearchRequest.getPageNo();
                List<User> usersInfo = userDao.getAllUsers(reqRecordCount, reqPageNo);
                modelMapper.map(usersInfo, usersResponse);
            } else {
                throw new MaxRecordLimitException("Maximum Limit Count reached (max_record_count = " + maxLimit + " )");
            }
        } catch (MaxRecordLimitException maxLimitException) {
            log.debug("##### Error Maximum Limit Count for Record Reached!!! ", maxLimitException);
            throw maxLimitException;
        } catch (Exception ex) {
            log.debug("##### Error while fetching users information in method => fetchUsers() ", ex);
            throw new IAMServiceException(ex.getMessage());
        }
        return usersResponse;
    }

}
