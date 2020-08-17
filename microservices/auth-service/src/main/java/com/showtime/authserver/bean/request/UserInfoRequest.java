package com.showtime.authserver.bean.request;

import lombok.Data;

/**
 * @author Vengatesan Nagarajan
 */
@Data
public class UserInfoRequest {
    private String email;
    private String phoneNo;
    private String password;
}
