package com.showtime.userservice.controller;

import com.showtime.userservice.bean.request.UserProfileRequest;
import com.showtime.userservice.domain.UserProfile;
import com.showtime.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * To Create User Profile Based on User Id of IAM
     *
     * @param userProfileRequest
     */
    public ResponseEntity<Void> createUserProfileDetails(@PathVariable("user-id") UUID userId, @RequestBody UserProfileRequest userProfileRequest) {
        userProfileRequest.setUserId(userId);
        UserProfile userProfile = userProfileService.createUserProfile(userProfileRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * To fetch the user profile based on used id
     *
     * @param userId
     * @return UserProfile
     */
    @GetMapping(path = "/profiles/{user-id}", produces = "application/json")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable("user-id") UUID userId) {
        UserProfile userProfile = userProfileService.fetchUserProfile(userId);
        return userProfile != null ? ResponseEntity.ok(userProfile) : ResponseEntity.notFound().build();
    }

    /**
     * To fetch all the users for admin dashboards
     *
     * @return UserProfile List
     */
    @GetMapping(path = "/profiles", produces = "application/json")
    public ResponseEntity<List<UserProfile>> getUserProfiles() {
        return ResponseEntity.ok(userProfileService.fetchUserProfiles());
    }

    // update or create

    /**
     * TO Delete User Profile based on User Id
     *
     * @param userId
     * @return
     */
    @DeleteMapping(path = "/profiles/{user-id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable("user-id") UUID userId) {
        userProfileService.deleteUserProfile(userId);
        return ResponseEntity.noContent().build();
    }
}
