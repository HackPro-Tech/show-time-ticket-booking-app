package com.showtime.userservice.bean.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
public class UserProfileRequest {

    private UUID userId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNo;
    private Date dateOfBirth;
    private Boolean whatsAppNotification;
    private String location;
    private String gender;
    private String profileImage;


}
