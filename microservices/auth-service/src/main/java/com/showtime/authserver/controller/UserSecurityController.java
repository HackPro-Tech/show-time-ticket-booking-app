
package com.showtime.authserver.controller;

import com.showtime.authserver.bean.request.UserProfileRequest;
import com.showtime.authserver.bean.request.UserSearchRequest;
import com.showtime.authserver.bean.response.UserResponse;
import com.showtime.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Vengatesan Nagarajan
 */
@RestController
//@RequestMapping("/security")
public class UserSecurityController {

    @Autowired
    private UserService userService;

    /**
     * To Register as new User
     *
     * @param userInfoRequest
     */
    @PostMapping("/register-new-user")
    public void registerNewUser(@RequestBody UserProfileRequest userInfoRequest) {
        userService.registerNewUser(userInfoRequest);
    }

    /**
     * To fetch all User information
     *
     * @param userSearchRequest
     * @return the List of user information
     */
    @PostMapping("/fetch-all-users")
    public ResponseEntity<List<UserResponse>> fetchUsers(@RequestBody UserSearchRequest userSearchRequest) {
        return ResponseEntity.ok(userService.fetchUsers(userSearchRequest));
    }

}
