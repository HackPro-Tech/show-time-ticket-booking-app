package com.showtime.userservice.bean.response;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Data
public class UserProfileResponse {

	private UUID userId;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileNo;
	private Integer age;
	private Boolean emailNotification;
	private Set<String> genre;

}
