package com.showtime.authserver.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.showtime.authserver.bean.request.UserInfoRequest;
import com.showtime.authserver.feign.fallbacks.UserProfileClientFallBack;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */

@FeignClient(name = "user-service", url = "http://localhost:8100/user", fallback = UserProfileClientFallBack.class)
public interface UserProfileClient {

	@PostMapping("/create-profile")
	public void createUserProfileDetails(@RequestBody UserInfoRequest userInfoRequest);

}
