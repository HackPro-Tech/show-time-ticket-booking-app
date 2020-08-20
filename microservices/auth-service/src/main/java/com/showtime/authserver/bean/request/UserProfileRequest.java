package com.showtime.authserver.bean.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Vengatesan Nagarajan
 */
@Data
public class UserProfileRequest {
    private String email;
    private String phoneNo;
    @NotNull
    private String password;
}
